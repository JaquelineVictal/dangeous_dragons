package tech.java.dangeous_dragons.infrastructure.persistence.repository.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

import java.util.Optional;

@Repository
public interface CharacterConfigRepository extends JpaRepository<CharacterConfig, Long> {
    Optional<CharacterConfig> findByCharacterType(CharacterTypeEnum characterType);
}
