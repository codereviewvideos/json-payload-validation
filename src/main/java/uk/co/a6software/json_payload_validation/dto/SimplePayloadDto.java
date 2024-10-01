package uk.co.a6software.json_payload_validation.dto;

import jakarta.validation.constraints.NotNull;

public record SimplePayloadDto(
        @NotNull(message = "Text must be provided")
        String text
) {
}
