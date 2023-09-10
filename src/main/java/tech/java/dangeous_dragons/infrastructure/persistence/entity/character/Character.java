package tech.java.dangeous_dragons.infrastructure.persistence.entity.character;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "character_type", referencedColumnName = "character_type")
    private CharacterConfig characterConfig;
}
