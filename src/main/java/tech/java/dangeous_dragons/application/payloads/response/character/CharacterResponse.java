package tech.java.dangeous_dragons.application.payloads.response.character;

import lombok.Data;

import java.time.Instant;

@Data
public class CharacterResponse {
    private Long id;
    private String name;
    private CharacterConfigResponse characterConfigResponse;
    private Instant createdAt;
    private Instant updatedAt;
}