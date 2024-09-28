package uk.co.a6software.json_payload_validation.dto;

import jakarta.validation.constraints.NotNull;

public class SimplePayloadDto {
    @NotNull(message = "Text must be provided")
    private String text;

    public SimplePayloadDto() {
    }

    public SimplePayloadDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
