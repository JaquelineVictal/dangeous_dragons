package tech.java.dangeous_dragons.infrastructure.persistence.entity.battle;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

import java.time.Instant;

@Data
@Entity
@Table(name = "battle")
@NoArgsConstructor
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "character_id", referencedColumnName = "id")
    private Character character;

    @ManyToOne
    @JoinColumn(name = "opponent_config_id", referencedColumnName = "id")
    private CharacterConfig opponentConfig;

    @ManyToOne
    @JoinColumn(name = "current_turn_id", referencedColumnName = "id")
    private Turn currentTurn;

    @Column(name = "first_attack")
    private String firstAttack;

    private Boolean isActive;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;
}
