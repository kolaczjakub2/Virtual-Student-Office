package com.example.demo.Controllers;

import com.example.demo.Commands.UserCommand;
import com.example.demo.Services.DeclarationService;
import com.example.demo.Services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@CrossOrigin
@RestController
public class UserController {


    private final UserService userService;
    private final DeclarationService declarationService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, DeclarationService declarationService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.declarationService = declarationService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/users/register")
    public ResponseEntity<Object> CreateUser(
            //@Valid
            @RequestBody UserCommand user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        UserCommand userCommand = userService.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCommand.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserCommand> Login(@RequestBody UserCommand user) {
        UserCommand userCommand = userService.login(user);
        HttpHeaders responseHeaders = new HttpHeaders();

        return new ResponseEntity<UserCommand>(userCommand, responseHeaders, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserCommand> getUser(@PathVariable Long id) {
        UserCommand userCommand = userService.getUser(id);
        userCommand.getDeclarationCommand().setDocumentCommands(
                declarationService.getDocuments(userCommand.getDeclarationCommand().getId()));
        return ResponseEntity.ok(userCommand);
    }

    @GetMapping("users/declarations")
    public ResponseEntity<Set<UserCommand>> getUsersWithDeclarations() {
        Set<UserCommand> userCommandSet = userService.getUsersWithDeclarations();
        for (UserCommand userCommand : userCommandSet) {
            userCommand.getDeclarationCommand().setDocumentCommands(
                    declarationService.getDocuments(userCommand.getDeclarationCommand().getId()));
        }
        return ResponseEntity.ok(userCommandSet);
    }


}
