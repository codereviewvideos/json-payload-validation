package uk.co.a6software.json_payload_validation.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.a6software.json_payload_validation.dto.SimplePayloadDto;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class SimpleJsonPayloadController {

    @PostMapping("/no-payload")
    public ResponseEntity<String> handleNoPayload() {
        return ResponseEntity.ok("yes this worked");
    }

    @PostMapping("/simple-payload")
    public ResponseEntity<?> handleSimplePayload(
            @Valid @RequestBody SimplePayloadDto simplePayloadDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            fieldError -> Optional
                                    .ofNullable(fieldError.getDefaultMessage())
                                    .orElse("Unknown error")
                    ));

            return ResponseEntity.badRequest().body(errors);
        }


        return ResponseEntity.ok("yes this also worked");
    }

}
