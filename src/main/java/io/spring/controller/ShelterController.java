package io.spring.controller;


import io.spring.service.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"shelters"})
public class ShelterController {

    private final ShelterService shelterService;

    public ShelterController(ShelterService shelterService) {
        this.shelterService = shelterService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getShelters() {
        return ResponseEntity.ok(shelterService.findAll());
    }
}
