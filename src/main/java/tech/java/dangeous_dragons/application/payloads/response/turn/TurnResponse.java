package tech.java.dangeous_dragons.application.payloads.response.turn;

import lombok.Data;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;

import java.time.Instant;

@Data
public class TurnResponse {
    private BattleResponse battleResponse;
    private Long id;
    private Integer turnOrder;
    private String attacker;
    private Integer diceAttack;
    private double attackForce;
    private Integer diceDefense;
    private double defenseForce;
    private Integer diceDamage;
    private double damageForce;
    private double initialHealthCharacter;
    private double finalHealthCharacter;
    private double initialHealthOpponent;
    private double finalHealthOpponent;
    private Instant createdAt;
    private Instant updatedAt;
}
