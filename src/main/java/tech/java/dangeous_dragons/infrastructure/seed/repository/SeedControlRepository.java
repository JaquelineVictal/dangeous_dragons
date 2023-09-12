package tech.java.dangeous_dragons.infrastructure.seed.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.java.dangeous_dragons.infrastructure.seed.entity.SeedControl;

public interface SeedControlRepository extends JpaRepository<SeedControl, Long> {
}