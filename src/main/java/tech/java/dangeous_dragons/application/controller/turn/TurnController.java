package tech.java.dangeous_dragons.application.controller.turn;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.java.dangeous_dragons.application.payloads.request.turn.TurnRequest;
import tech.java.dangeous_dragons.application.payloads.response.turn.HistoryTurnResponse;
import tech.java.dangeous_dragons.domain.service.turn.CreateTurnService;


@RestController
@RequestMapping("/turn")
@RequiredArgsConstructor
public class TurnController {
    private final CreateTurnService createTurnService;

    @PostMapping(path = "", produces = "application/json")
    public HistoryTurnResponse createTurn(@RequestBody @Valid TurnRequest turnRequest) {
        return createTurnService.execute(turnRequest);
    }
}
