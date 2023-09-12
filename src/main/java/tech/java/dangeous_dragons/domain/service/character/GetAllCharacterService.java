package tech.java.dangeous_dragons.domain.service.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.CharacterToCreateCharacterResponseMapper;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCharacterService {

    private final CharacterRepository characterRepository;

    public List<CharacterResponse> execute() {
        List<Character> activeCharacters = characterRepository.findByDeletedAtIsNull();

        return activeCharacters
                .stream()
                .map(CharacterToCreateCharacterResponseMapper::execute)
                .toList();
    }
}
