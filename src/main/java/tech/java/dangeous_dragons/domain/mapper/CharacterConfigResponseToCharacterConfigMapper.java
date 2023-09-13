package tech.java.dangeous_dragons.domain.mapper;

import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

public class CharacterConfigResponseToCharacterConfigMapper {

    public static CharacterConfig execute(CharacterConfigResponse characterConfigResponse) {

        CharacterConfig characterConfig = new CharacterConfig();

        characterConfig.setId(characterConfigResponse.getId());
        characterConfig.setHero(characterConfigResponse.isHero());
        characterConfig.setCharacterType(characterConfigResponse.getCharacterType());
        characterConfig.setHealthPoints(characterConfigResponse.getHealthPoints());
        characterConfig.setAttack(characterConfigResponse.getAttack());
        characterConfig.setDefense(characterConfigResponse.getDefense());
        characterConfig.setAgility(characterConfigResponse.getAgility());
        characterConfig.setQuantityDice(characterConfigResponse.getQuantityDice());
        characterConfig.setDiceFaces(characterConfigResponse.getDiceFaces());

        return characterConfig;
    }
}
