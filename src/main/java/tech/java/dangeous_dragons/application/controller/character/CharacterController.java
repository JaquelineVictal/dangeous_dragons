package tech.java.dangeous_dragons.application.controller.character;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.java.dangeous_dragons.application.payloads.request.character.CharacterRequest;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.domain.service.character.*;

import java.util.List;

@RestController
@RequestMapping("/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CreateCharacterService createCharacterService;
    private final UpdateCharacterService updateCharacterService;
    private final DeleteCharacterService deleteCharacterService;
    private final GetByIdCharacterService getByIdCharacterService;
    private final GetAllCharacterService getAllCharacterService;

    @PostMapping(path = "", produces = "application/json")
    public CharacterResponse createCharacter(@RequestBody @Valid CharacterRequest createCharacterRequest) {
        return createCharacterService.execute(createCharacterRequest);
    }

    @Transactional
    @PutMapping(path = "/{idCharacter}")
    public CharacterResponse updateCharacter(
            @PathVariable Long idCharacter,
            @RequestBody @Valid CharacterRequest createCharacterRequest) {
        return updateCharacterService.execute(idCharacter, createCharacterRequest);
    }

    @Transactional
    @DeleteMapping(path = "/{idCharacter}")
    public String deleteCharacter(@PathVariable Long idCharacter) {
        return deleteCharacterService.execute(idCharacter);
    }

    @Transactional
    @GetMapping(path = "/{idCharacter}")
    public CharacterResponse getByIdCharacter(@PathVariable Long idCharacter) {
        return getByIdCharacterService.execute(idCharacter);
    }

    @Transactional
    @GetMapping(path = "")
    public List<CharacterResponse> getAllCharacter() {
        return getAllCharacterService.execute();
    }

    
}
