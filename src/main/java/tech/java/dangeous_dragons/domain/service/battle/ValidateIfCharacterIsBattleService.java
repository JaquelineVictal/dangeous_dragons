package tech.java.dangeous_dragons.domain.service.battle;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.common.exceptions.CharacterInBattleException;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;

@Service
@RequiredArgsConstructor
public class ValidateIfCharacterIsBattleService {
    private final BattleRepository battleRepository;

    public void execute(Long characterId) {
        Boolean isExist = battleRepository.existsActiveBattleForCharacter(characterId);

        if (Boolean.TRUE.equals(isExist)) throw new CharacterInBattleException();
    }
}
