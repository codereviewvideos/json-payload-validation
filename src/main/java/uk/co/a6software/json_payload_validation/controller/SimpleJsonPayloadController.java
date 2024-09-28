package uk.co.a6software.json_payload_validation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SimpleJsonPayloadController {

    @PostMapping("/simple")
    public ResponseEntity<String> handle() {
        return ResponseEntity.ok("yes this worked");
    }
}
