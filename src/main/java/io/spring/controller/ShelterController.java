package io.spring.controller;


import io.spring.model.Shelter;
import io.spring.service.ShelterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    public ResponseEntity<?> addShelter(@RequestBody(required = false) Shelter shelter) {
        try {
            return ResponseEntity.ok(shelterService.upsertForUser(shelter));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
