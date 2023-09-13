package tech.java.dangeous_dragons.application.mapper;

import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;

public class BattleToBattleResponseMapper {
    public static BattleResponse execute(Battle battle) {
        CharacterResponse characterResponse = CharacterToCreateCharacterResponseMapper.execute(battle.getCharacter());
        CharacterConfigResponse opponentConfig = CharacterConfigToCharacterConfigResponseMapper.execute(battle.getOpponentConfig());

        BattleResponse battleResponse = new BattleResponse();
        battleResponse.setId(battle.getId());
        battleResponse.setCharacterResponse(characterResponse);
        battleResponse.setOpponentConfigResponse(opponentConfig);
        battleResponse.setFirstAttack(battle.getFirstAttack());
        battleResponse.setCurrentTurn(battleResponse.getCurrentTurn());
        battleResponse.setIsActive(battle.getIsActive());
        battleResponse.setCreatedAt(battle.getCreatedAt());
        battleResponse.setUpdatedAt(battle.getUpdatedAt());

        return battleResponse;
    }
}
