package tech.java.dangeous_dragons.domain.mapper;


import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

public class BattleReponseToBattleMapper {
    public static Battle execute(BattleResponse battleResponse) {
        Character character = CharacterResponseToCharacterMapper.execute(battleResponse.getCharacterResponse());
        CharacterConfig opponentConfig = CharacterConfigResponseToCharacterConfigMapper.execute(battleResponse.getOpponentConfigResponse());

        Battle battle = new Battle();
        battle.setId(battleResponse.getId());
        battle.setCharacter(character);
        battle.setOpponentConfig(opponentConfig);
        battle.setFirstAttack(battleResponse.getFirstAttack());
        battle.setCurrentTurn(battleResponse.getCurrentTurn());
        battle.setIsActive(battleResponse.getIsActive());

        return battle;
    }
}
