package tech.java.dangeous_dragons.application.controller.character;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.java.dangeous_dragons.application.payloads.request.character.CreateCharacterRequest;

@RestController
@RequestMapping(name = "/character")
@RequiredArgsConstructor
public class CharacterController {

    @PostMapping(path = "", produces = "application/json")
    public void createCharacter(@RequestBody @Valid CreateCharacterRequest dto) {
        System.out.println(dto);
    }
}
