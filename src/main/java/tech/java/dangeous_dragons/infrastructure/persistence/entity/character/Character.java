package tech.java.dangeous_dragons.infrastructure.persistence.entity.character;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "character")
@NoArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "character_config_id", referencedColumnName = "id")
    private CharacterConfig characterConfig;
}
