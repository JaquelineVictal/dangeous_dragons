package tech.java.dangeous_dragons.domain.service.battle;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.BattleToBattleResponseMapper;
import tech.java.dangeous_dragons.application.payloads.request.battle.BattleRequest;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;
import tech.java.dangeous_dragons.common.enums.MonsterTypeEnum;
import tech.java.dangeous_dragons.domain.mapper.CharacterResponseToCharacterMapper;
import tech.java.dangeous_dragons.domain.service.character.GetByIdCharacterService;
import tech.java.dangeous_dragons.domain.service.character.GetCharacterConfigService;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreateBattleService {
    private final GetByIdCharacterService getByIdCharacterService;
    private final GetCharacterConfigService getCharacterConfigService;
    private final BattleRepository battleRepository;
    private final Random random = new Random();

    public BattleResponse execute(BattleRequest battleRequest) {
        CharacterResponse characterResponse = getByIdCharacterService.execute(battleRequest.characterId());
        CharacterConfig opponentConfig = getOpponent(battleRequest.opponentType());
        String firstAttack = getFirstAttack(characterResponse, opponentConfig);

        Character character = CharacterResponseToCharacterMapper.execute(characterResponse);

        Battle battle = new Battle();
        battle.setCharacter(character);
        battle.setOpponentConfig(opponentConfig);
        battle.setFirstAttack(firstAttack);
        battle.setIsActive(true);

        Battle battleSaved = battleRepository.save(battle);
        return BattleToBattleResponseMapper.execute(battleSaved);

    }

    private CharacterConfig getOpponent(Optional<MonsterTypeEnum> opponentType) {
        if (opponentType.isPresent()) {
            MonsterTypeEnum monsterTypeEnum = opponentType.get();
            CharacterTypeEnum characterTypeEnum = CharacterTypeEnum.valueOf(monsterTypeEnum.toString());
            return getCharacterConfigService.execute(characterTypeEnum);
        }
        MonsterTypeEnum randoMonsterType = getRandoMonsterType();
        CharacterTypeEnum characterTypeEnum = CharacterTypeEnum.valueOf(randoMonsterType.toString());
        return getCharacterConfigService.execute(characterTypeEnum);
    }

    private MonsterTypeEnum getRandoMonsterType() {
        MonsterTypeEnum[] monsterTypes = MonsterTypeEnum.values();

        int randomIndex = random.nextInt(monsterTypes.length);
        return monsterTypes[randomIndex];
    }

    private String getFirstAttack(CharacterResponse character, CharacterConfig opponent) {
        int initiativeCharacter = random.nextInt(20);
        int initiativeOpponent = random.nextInt(20);

        if (initiativeCharacter != initiativeOpponent) {
            return initiativeCharacter > initiativeOpponent ? character.getName() : opponent.getCharacterType().toString();
        }

        return getFirstAttack(character, opponent);
    }
}
