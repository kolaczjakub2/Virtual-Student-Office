package com.example.demo.Converters;

import com.example.demo.Commands.DeclarationCommand;
import com.example.demo.Commands.DocumentCommand;
import com.example.demo.Domain.Declaration;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DeclarationCommandToDeclaration implements Converter<DeclarationCommand, Declaration> {

    private final DocumentCommandToDocument documentCommandToDocument;

    public DeclarationCommandToDeclaration(DocumentCommandToDocument documentCommandToDocument) {
        this.documentCommandToDocument = documentCommandToDocument;
    }

    @Synchronized
    @Nullable
    @Override
    public Declaration convert(DeclarationCommand declarationCommand) {

        final Declaration declaration = new Declaration();
        declaration.setLocalDate(declarationCommand.getDate());
        declaration.setId(declarationCommand.getId());
        declaration.setStatus(declarationCommand.getStatus());
        declaration.setLocalTime(declarationCommand.getTime());
        declaration.setRoom(declarationCommand.getRoom());
        if (declaration.getDocuments() != null) {
            for (DocumentCommand documentCommand : declarationCommand.getDocumentCommands())
                declaration.getDocuments().add(documentCommandToDocument.convert(documentCommand));
        }
        return declaration;
    }
}
