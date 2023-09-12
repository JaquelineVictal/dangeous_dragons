package tech.java.dangeous_dragons.application.controller.character;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.java.dangeous_dragons.application.payloads.request.character.CreateCharacterRequest;
import tech.java.dangeous_dragons.application.payloads.response.character.CreateCharacterResponse;
import tech.java.dangeous_dragons.domain.service.character.CreateCharacterService;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CreateCharacterService createCharacterService;

    @PostMapping(path = "", produces = "application/json")
    public CreateCharacterResponse createCharacter(@RequestBody @Valid CreateCharacterRequest createCharacterRequest) {
        return createCharacterService.execute(createCharacterRequest);
    }
}
