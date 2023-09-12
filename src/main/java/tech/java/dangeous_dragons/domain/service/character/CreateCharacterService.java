package tech.java.dangeous_dragons.domain.service.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.CharacterToCreateCharacterResponseMapper;
import tech.java.dangeous_dragons.application.payloads.request.character.CreateCharacterRequest;
import tech.java.dangeous_dragons.application.payloads.response.character.CreateCharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterRepository;

@Service
@RequiredArgsConstructor
public class CreateCharacterService {

    private final CharacterRepository characterRepository;
    private final GetCharacterConfigService getCharacterConfigService;


    public CreateCharacterResponse execute(CreateCharacterRequest createCharacterRequest) {
        CharacterConfig characterConfig = getCharacterConfigService.execute(createCharacterRequest.characterType());

        Character character = new Character();
        character.setName(createCharacterRequest.name());
        character.setCharacterConfig(characterConfig);

        Character characterSaved = characterRepository.save(character);
        
        return CharacterToCreateCharacterResponseMapper.execute(characterSaved);
    }
}
