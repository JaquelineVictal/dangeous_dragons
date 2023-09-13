package tech.java.dangeous_dragons.application.payloads.request.turn;

import jakarta.validation.constraints.NotNull;

public record TurnRequest(
        @NotNull
        Long battleId) {
}
