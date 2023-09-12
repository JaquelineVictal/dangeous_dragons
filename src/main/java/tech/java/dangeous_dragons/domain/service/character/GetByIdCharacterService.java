package tech.java.dangeous_dragons.domain.service.character;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.CharacterToCreateCharacterResponseMapper;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterRepository;

@Service
@RequiredArgsConstructor
public class GetByIdCharacterService {

    private final CharacterRepository characterRepository;

    public CharacterResponse execute(Long id) {
        Character character = characterRepository.getReferenceById(id);
        return CharacterToCreateCharacterResponseMapper.execute(character);
    }
}
