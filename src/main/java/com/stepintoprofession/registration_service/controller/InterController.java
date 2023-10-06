package com.stepintoprofession.registration_service.controller;

import com.stepintoprofession.registration_service.model.Intern;
import com.stepintoprofession.registration_service.service.InternService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interns")
public class InterController {

    private final InternService internService;

    public InterController(InternService internService) {
        this.internService = internService;
    }

    @PostMapping
    public ResponseEntity<Intern> addIntern(@RequestBody Intern intern){
        return ResponseEntity.ok(internService.save(intern));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntern(@PathVariable Integer id) {
        return internService.delete(id);
    }

    @PatchMapping("")
    public ResponseEntity<Intern> updateIntern(@RequestBody Intern body) {
        return ResponseEntity.ok(internService.save(body));
    }

    @GetMapping
    public List<Intern> findAll() {
        return internService.findALL();
    }

    @GetMapping("/findBy")
    public Intern findBy(@RequestParam String firstName, @RequestParam String lastName) {
        return internService.findByFirstNamesAndLastName(firstName, lastName);
    }
}
