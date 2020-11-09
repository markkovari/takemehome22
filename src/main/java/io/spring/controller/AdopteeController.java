package io.spring.controller;


import io.spring.dto.CreateAdoptee;
import io.spring.model.Adoptee;
import io.spring.service.AdopteeService;
import io.spring.service.ShelterService;
import io.spring.util.exception.UserHasNoShelterYetException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"adoptees"})
public class AdopteeController {
    private final ShelterService shelterService;
    private final AdopteeService adopteeService;


    public AdopteeController(ShelterService shelterService, AdopteeService adopteeService) {
        this.shelterService = shelterService;
        this.adopteeService = adopteeService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getAdoptees() {
        return ResponseEntity.ok(adopteeService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<?> addAdoptee(@RequestBody CreateAdoptee createAdoptee) {
        try {
            Adoptee adoptee = shelterService.addAdoptee(createAdoptee);
            return ResponseEntity.ok(adoptee);
        } catch (UserHasNoShelterYetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
