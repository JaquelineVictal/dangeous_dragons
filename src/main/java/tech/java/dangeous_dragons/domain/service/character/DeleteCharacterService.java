package tech.java.dangeous_dragons.domain.service.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class DeleteCharacterService {
    private final CharacterRepository characterRepository;

    public String execute(Long id) {
        Character character = characterRepository.getReferenceById(id);
        //TODO: validate if the character is in a battle

        character.setDeletedAt(Instant.now());
        characterRepository.save(character);
        return "Character successfully deleted";
    }
}
