package tech.java.dangeous_dragons.infrastructure.persistence.repository.battle;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;

import java.util.Optional;

@Repository
public interface BattleRepository extends JpaRepository<Battle, Long> {
    Optional<Battle> findByIdAndIsActiveTrue(Long id);

    @Query("SELECT COUNT(b) > 0 FROM Battle b WHERE b.character.id = :characterId AND b.isActive = true")
    boolean existsActiveBattleForCharacter(@Param("characterId") Long characterId);

}
