package tech.java.dangeous_dragons.infrastructure.persistence.entity.turn;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;

import java.time.Instant;

@Data
@Entity
@Table(name = "turn")
@NoArgsConstructor
public class Turn {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "battle_id", referencedColumnName = "id")
    private Battle battle;

    @Column(name = "turn_order")
    private Integer turnOrder;

    private String attacker;

    @Column(name = "dice_attack")
    private Integer diceAttack;

    @Column(name = "attack_force")
    private double attackForce;

    @Column(name = "dice_defense")
    private Integer diceDefense;

    @Column(name = "defense_force")
    private double defenseForce;

    @Column(name = "dice_damage")
    private Integer diceDamage;

    @Column(name = "damage_force")
    private double damageForce;

    @Column(name = "initial_health_character")
    private double initialHealthCharacter;

    @Column(name = "final_health_character")
    private double finalHealthCharacter;

    @Column(name = "initial_health_opponent")
    private double initialHealthOpponent;

    @Column(name = "final_health_opponent")
    private double finalHealthOpponent;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;
}
