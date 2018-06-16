package com.example.demo.Services;

import com.example.demo.Commands.DeclarationCommand;
import com.example.demo.Commands.DocumentCommand;
import com.example.demo.Converters.DeclarationToDeclarationCommand;
import com.example.demo.Converters.DocumentCommandToDocument;
import com.example.demo.Converters.DocumentToDocumentCommand;
import com.example.demo.Domain.Declaration;
import com.example.demo.Domain.Document;
import com.example.demo.Domain.User;
import com.example.demo.PDFGenerator.PDFGenerator;
import com.example.demo.Repositories.DeclarationRepository;
import com.example.demo.Repositories.DocumentRepository;
import com.example.demo.Repositories.UserRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class DeclarationServiceImpl implements DeclarationService {

    private final UserRepository userRepository;
    private final DeclarationRepository declarationRepository;
    private final DocumentRepository documentRepository;
    private final DeclarationToDeclarationCommand declarationToDeclarationCommand;
    private final DocumentCommandToDocument documentCommandToDocument;
    private final DocumentToDocumentCommand documentToDocumentCommand;

    public DeclarationServiceImpl(DeclarationRepository declarationRepository,
                                  DeclarationToDeclarationCommand declarationToDeclarationCommand,
                                  DocumentCommandToDocument documentCommandToDocument,
                                  UserRepository userRepository,
                                  DocumentRepository documentRepository,
                                  DocumentToDocumentCommand documentToDocumentCommand) {
        this.declarationRepository = declarationRepository;
        this.declarationToDeclarationCommand = declarationToDeclarationCommand;
        this.documentCommandToDocument = documentCommandToDocument;
        this.userRepository = userRepository;
        this.documentRepository = documentRepository;
        this.documentToDocumentCommand = documentToDocumentCommand;
    }

    @Override
    public Set<DeclarationCommand> getDeclarations(String room, LocalDate date) {
        Set<Declaration> declarationSet = new HashSet<>();
        Set<DeclarationCommand> declarationCommandSet = new HashSet<>();
        declarationRepository.findAll().iterator().forEachRemaining(declarationSet::add);
        for (Declaration declaration : declarationSet) {
            if (declaration.getStatus().equals("Free") &&
                    declaration.getLocalDate().equals(date) &&
                    declaration.getRoom().equals(room))
                declarationCommandSet.add(declarationToDeclarationCommand.convert(declaration));
        }
        return declarationCommandSet;
    }

    @Override
    public void assignUserToDeclaration(Long declarationId, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Declaration> optionalDeclaration = declarationRepository.findById(declarationId);

        if (optionalDeclaration.isPresent() && optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getDeclaration() != null) {
                Declaration alreadyDeclaration = user.getDeclaration();
                alreadyDeclaration.setStatus("Free");
            }
            Declaration declaration = optionalDeclaration.get();
            declaration.setStatus("Acceptable");
            user.setDeclaration(declaration);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Declaration or User didn't exist");
        }
    }

    @Override
    public void assignDocumentToDeclaration(Long declarationId, DocumentCommand documentCommand) {
        Optional<Declaration> optionalDeclaration = declarationRepository.findById(declarationId);
        if (optionalDeclaration.isPresent()) {
            Document document = documentCommandToDocument.convert(documentCommand);
            Document savedDocument = documentRepository.save(document);
            Declaration declaration = optionalDeclaration.get();
            declaration.getDocuments().add(savedDocument);
            declarationRepository.save(declaration);
        }
    }

    @Override
    public Set<DocumentCommand> getDocuments(Long declarationId) {
        Optional<Declaration> optionalDeclaration = declarationRepository.findById(declarationId);
        if (optionalDeclaration.isPresent()) {
            Declaration declaration = optionalDeclaration.get();
            if (declaration.getDocuments() != null) {
                Set<DocumentCommand> documentCommands = new HashSet<>();
                for (Document document : declaration.getDocuments()) {
                    documentCommands.add(documentToDocumentCommand.convert(document));
                }
                return documentCommands;
            }
        }
        return null;
    }

    @Override
    public DocumentCommand generatePDF(ArrayList<String> selectedRooms, ArrayList<String> selectedDates) {
        ArrayList<LocalDate> dates = new ArrayList<>();
        for (String date : selectedDates)
            dates.add(LocalDate.parse(date));

        Optional<List<User>> optionalUsers = userRepository.findUsersWithDeclarationsByRoomAndDate(selectedRooms, dates);
        if (optionalUsers.isPresent()) {
            List<User> users = optionalUsers.get();
            PDFGenerator.getInstance().generateDocument(users, selectedRooms, dates);
            File file=new File("Deklaracje.pdf");
            String fileType = "Undetermined";
            try {
                fileType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Document document=new Document();
            document.setFileType(fileType);
            document.setFileName(file.getName());
            byte[] encodedBytes = new byte[0];
            try {
                encodedBytes = Base64.encodeBase64(Files.readAllBytes(file.toPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String pdfInBase64 = new String(encodedBytes);
            document.setData(pdfInBase64);
            return documentToDocumentCommand.convert(document);
        }

        throw new RuntimeException("Didn't exist");

    }

    @Override
    public Set<String> getRoomNames() {
        return declarationRepository.findRoomNames();
    }

    @Override
    public Set<LocalDate> getAvailableDatesForRoom(String name) {
        Set<Date> dates = declarationRepository.getAvailableDatesForRoom(name);
        Set<LocalDate> localDates = new HashSet<>();
        for (Date date : dates)
            localDates.add(date.toLocalDate());

        return localDates;
    }
}
