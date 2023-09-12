package tech.java.dangeous_dragons.application.payloads.response.character;

import lombok.Data;

@Data
public class CreateCharacterResponse {
    private Long id;
    private String name;
    private CreateCharacterConfigResponse createCharacterConfigResponse;
}