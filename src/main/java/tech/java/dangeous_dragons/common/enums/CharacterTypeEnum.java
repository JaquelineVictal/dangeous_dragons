package tech.java.dangeous_dragons.common.enums;


import java.util.EnumSet;
import java.util.Set;

public enum CharacterTypeEnum {
    ;

    static {
        Set<CharacterTypeEnum> types = EnumSet.noneOf(CharacterTypeEnum.class);

        for (HeroTypeEnum heroType : HeroTypeEnum.values()) {
            types.add(CharacterTypeEnum.valueOf(heroType.name()));
        }

        for (MonsterTypeEnum monsterType : MonsterTypeEnum.values()) {
            types.add(CharacterTypeEnum.valueOf(monsterType.name()));
        }
    }
}