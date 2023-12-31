package tech.java.dangeous_dragons.infrastructure.persistence.entity.character;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;

@Data
@Setter
@Entity
@Table(name = "character_config")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
public class CharacterConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_hero")
    private boolean isHero;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", unique = true)
    private CharacterTypeEnum characterType;

    @Column(name = "health_points")
    private double healthPoints;

    private double attack;

    private double defense;

    private double agility;

    @Column(name = "quantity_dice")
    private int quantityDice;

    @Column(name = "dice-faces")
    private int diceFaces;
}
