package tech.java.dangeous_dragons.application.controller.battle;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.java.dangeous_dragons.application.payloads.request.battle.BattleRequest;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.domain.service.battle.CreateBattleService;

@RestController
@RequestMapping("/battle")
@RequiredArgsConstructor
public class BattleController {
    private final CreateBattleService createBattleService;

    @PostMapping(path = "", produces = "application/json")
    public BattleResponse createBattle(@RequestBody @Valid BattleRequest battleRequest) {
        return createBattleService.execute(battleRequest);
    }
}
