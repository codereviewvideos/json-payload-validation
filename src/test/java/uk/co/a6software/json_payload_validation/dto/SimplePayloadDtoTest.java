package uk.co.a6software.json_payload_validation.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SimplePayloadDtoTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenTextIsNull_thenValidationFails() {
        SimplePayloadDto payload = new SimplePayloadDto(null);

        Set<ConstraintViolation<SimplePayloadDto>> violations = validator.validate(payload);

        assertFalse(violations.isEmpty());
        assertTrue(violations
                .stream()
                .anyMatch(v -> v.getMessage().equals("Text must be provided")));
    }

    @Test
    void whenTextIsProvided_thenValidationPasses() {
        SimplePayloadDto payload = new SimplePayloadDto("Some text");

        Set<ConstraintViolation<SimplePayloadDto>> violations = validator.validate(payload);

        assertTrue(violations.isEmpty());
    }
}
