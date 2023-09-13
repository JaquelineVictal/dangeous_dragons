package tech.java.dangeous_dragons.infrastructure.persistence.repository.battle;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {

}