package tech.java.dangeous_dragons.infrastructure.seed.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "seed_control")
@NoArgsConstructor
public class SeedControl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seed_executed")
    private boolean seedExecuted;
}
