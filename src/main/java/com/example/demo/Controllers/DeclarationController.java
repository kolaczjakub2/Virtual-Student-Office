package com.example.demo.Controllers;

import com.example.demo.Commands.DeclarationCommand;
import com.example.demo.Commands.DocumentCommand;
import com.example.demo.Services.DeclarationService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class DeclarationController {

    private final DeclarationService declarationService;

    public DeclarationController(DeclarationService declarationService) {
        this.declarationService = declarationService;
    }

    @GetMapping("declarations/rooms/{room}/dates/{date}")
    public ResponseEntity<List<DeclarationCommand>> getAllFree(
            @PathVariable String room,
            @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<DeclarationCommand> declarationCommands = declarationService.getDeclarations(room, localDate).stream().sorted((Comparator.comparing(DeclarationCommand::getTime))).collect(Collectors.toList());
        return ResponseEntity.ok(declarationCommands);
    }

    @GetMapping("declarations/rooms/names")
    public ResponseEntity<Set<String>> getRoomNames() {
        Set<String> roomNames = declarationService.getRoomNames();
        return ResponseEntity.ok(roomNames);
    }

    @GetMapping("declarations/rooms/{name}/dates")
    public ResponseEntity<Set<LocalDate>> getAvailableDatesForRoom(
            @PathVariable String name) {
        Set<LocalDate> dates = declarationService.getAvailableDatesForRoom(name);
        return ResponseEntity.ok(dates);
    }

    @PutMapping("declarations/{declarationId}/user/{userId}")
    public ResponseEntity<Object> assignUserToDeclaration(
            @PathVariable Long declarationId,
            @PathVariable Long userId) {
        declarationService.assignUserToDeclaration(declarationId, userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("declarations/{declarationId}")
    public ResponseEntity<Object> assignDocumentToDeclaration(@PathVariable Long declarationId, @RequestBody Object obj) {
        DocumentCommand documentCommand = new DocumentCommand();
        HashMap<String, String> map = (HashMap<String, String>) obj;
        documentCommand.setDisplayName(map.get("name"));
        documentCommand.setData(map.get("data"));
        documentCommand.setFileName(map.get("filename"));
        documentCommand.setFileType(map.get("filetype"));

        declarationService.assignDocumentToDeclaration(declarationId, documentCommand);
        return ResponseEntity.ok().build();
    }

    @PutMapping("declarations/pdf")
    public ResponseEntity<DocumentCommand> downloadPdf(@RequestBody Object obj) {
        HashMap<String, ArrayList<String>> map = (HashMap<String, ArrayList<String>>) obj;
        DocumentCommand documentCommand = declarationService.generatePDF(map.get("selectedRooms"), map.get("selectedDates"));
        return ResponseEntity.ok(documentCommand);
    }
}
