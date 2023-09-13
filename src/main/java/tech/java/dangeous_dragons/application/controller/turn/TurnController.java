package tech.java.dangeous_dragons.application.controller.turn;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.java.dangeous_dragons.application.payloads.request.turn.TurnRequest;


@RestController
@RequestMapping("/turn")
@RequiredArgsConstructor
public class TurnController {


    @PostMapping(path = "", produces = "application/json")
    public void createBattle(@RequestBody @Valid TurnRequest turnRequest) {

    }
}
