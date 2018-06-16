package com.example.demo.Services;

import com.example.demo.Commands.DeclarationCommand;
import com.example.demo.Commands.DocumentCommand;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public interface DeclarationService {
    Set<String> getRoomNames();

    Set<LocalDate> getAvailableDatesForRoom(String name);

    Set<DeclarationCommand> getDeclarations(String room, LocalDate date);

    void assignUserToDeclaration(Long declarationId, Long userId);

    void assignDocumentToDeclaration(Long declarationId, DocumentCommand documentCommand);

    Set<DocumentCommand> getDocuments(Long id);

    DocumentCommand generatePDF(ArrayList<String> selectedRooms, ArrayList<String> selectedDates);
}
