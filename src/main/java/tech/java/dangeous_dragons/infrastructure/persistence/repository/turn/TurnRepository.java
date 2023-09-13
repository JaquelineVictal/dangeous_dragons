package tech.java.dangeous_dragons.infrastructure.persistence.repository.turn;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

@Repository
public interface TurnRepository extends JpaRepository<Turn, Long> {

}
