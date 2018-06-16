package com.example.demo.Controllers;

import com.example.demo.Commands.TermCommand;
import com.example.demo.Services.TermService;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Set;

@CrossOrigin
@RestController
public class TermController {

    private final TermService termService;

    public TermController(TermService termService) {
        this.termService = termService;
    }


    @PostMapping("terms")
    public ResponseEntity<Object> saveTerm(@Valid @RequestBody TermCommand TermCommand) {
        TermCommand savedTerm = termService.saveTerm(TermCommand);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTerm.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("terms/{id}")
    public ResponseEntity<TermCommand> getTermById(@PathVariable Long id) {
        TermCommand termCommand = termService.getTerm(id);
        return ResponseEntity.ok(termCommand);
    }

    @GetMapping("terms")
    public ResponseEntity<Set<TermCommand>> getTerms() {
        Set<TermCommand> termCommandSet = termService.getAllTerms();
        return ResponseEntity.ok(termCommandSet);
    }

    @DeleteMapping("terms/{Id}")
    public ResponseEntity<Object> deleteTerm(@PathVariable Long Id) {
        TermCommand termCommand = termService.deleteTerm(Id);
        return ResponseEntity.accepted().build();
    }

}
