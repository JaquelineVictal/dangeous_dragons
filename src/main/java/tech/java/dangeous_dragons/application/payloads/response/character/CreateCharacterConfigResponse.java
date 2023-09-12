package tech.java.dangeous_dragons.application.payloads.response.character;

import lombok.Data;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;

@Data
public class CreateCharacterConfigResponse {
    private long id;
    private boolean isHero;
    private CharacterTypeEnum characterType;
    private double healthPoints;
    private double attack;
    private double defense;
    private double agility;
    private int quantityDice;
    private int diceFaces;
}
