package tech.java.dangeous_dragons.infrastructure.persistence.repository.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {
    Optional<Character> findByIdAndDeletedAtIsNull(Long id);

    List<Character> findByDeletedAtIsNull();
}