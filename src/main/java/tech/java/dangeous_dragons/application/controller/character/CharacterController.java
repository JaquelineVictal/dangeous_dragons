package tech.java.dangeous_dragons.application.controller.character;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.java.dangeous_dragons.application.payloads.request.character.CharacterRequest;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.domain.service.character.CreateCharacterService;
import tech.java.dangeous_dragons.domain.service.character.UpdateCharacterService;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CreateCharacterService createCharacterService;
    private final UpdateCharacterService updateCharacterService;

    @PostMapping(path = "", produces = "application/json")
    public CharacterResponse createCharacter(@RequestBody @Valid CharacterRequest createCharacterRequest) {
        return createCharacterService.execute(createCharacterRequest);
    }

    @Transactional
    @PutMapping(path = "/{idCharacter}")
    public CharacterResponse updateCharacter(
            @PathVariable Long idCharacter,
            @RequestBody @Valid CharacterRequest createCharacterRequest) {
        System.out.println(idCharacter);
        return updateCharacterService.execute(idCharacter, createCharacterRequest);
    }
}
