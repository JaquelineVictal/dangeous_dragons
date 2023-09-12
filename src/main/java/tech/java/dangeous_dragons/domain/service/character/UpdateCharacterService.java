package tech.java.dangeous_dragons.domain.service.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.CharacterToCreateCharacterResponseMapper;
import tech.java.dangeous_dragons.application.payloads.request.character.CharacterRequest;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterRepository;

@Service
@RequiredArgsConstructor
public class UpdateCharacterService {

    private final CharacterRepository characterRepository;
    private final GetCharacterConfigService getCharacterConfigService;

    public CharacterResponse execute(Long id, CharacterRequest createCharacterRequest) {
        Character character = characterRepository.getReferenceById(id);
        //TODO: validate if the character is in a battle
        CharacterConfig characterConfig = getCharacterConfigService.execute(createCharacterRequest.characterType());

        character.setName(createCharacterRequest.name());
        character.setCharacterConfig(characterConfig);

        Character characterUpdated = characterRepository.save(character);
        return CharacterToCreateCharacterResponseMapper.execute(characterUpdated);
    }
}
