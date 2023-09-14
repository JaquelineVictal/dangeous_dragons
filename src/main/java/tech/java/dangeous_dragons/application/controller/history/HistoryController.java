package tech.java.dangeous_dragons.application.controller.history;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.java.dangeous_dragons.application.payloads.response.turn.HistoryTurnResponse;
import tech.java.dangeous_dragons.domain.service.history.GetHistoryByBattleService;


@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class HistoryController {
    private final GetHistoryByBattleService getHistoryByBattleService;

    @Transactional
    @GetMapping(path = "/{idBattle}")
    public HistoryTurnResponse getHistory(@PathVariable Long idBattle) {
        return getHistoryByBattleService.execute(idBattle);
    }
}
