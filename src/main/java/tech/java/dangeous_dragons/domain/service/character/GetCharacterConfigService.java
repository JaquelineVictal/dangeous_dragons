package tech.java.dangeous_dragons.domain.service.character;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;
import tech.java.dangeous_dragons.common.exceptions.NotFoundException;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.character.CharacterConfigRepository;

@Service
@RequiredArgsConstructor
public class GetCharacterConfigService {

    private final CharacterConfigRepository characterConfigRepository;

    public CharacterConfig execute(CharacterTypeEnum characterType) {
        return characterConfigRepository.findByCharacterType(characterType)
                .orElseThrow(() -> new NotFoundException("CharacterConfig"));
    }
}
