package com.example.demo.Converters;


import com.example.demo.Commands.DeclarationCommand;
import com.example.demo.Commands.DocumentCommand;
import com.example.demo.Domain.Declaration;
import com.example.demo.Domain.Document;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class DeclarationToDeclarationCommand implements Converter<Declaration, DeclarationCommand> {

    private final DocumentToDocumentCommand documentToDocumentCommand;

    public DeclarationToDeclarationCommand(DocumentToDocumentCommand documentToDocumentCommand) {
        this.documentToDocumentCommand = documentToDocumentCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public DeclarationCommand convert(Declaration declaration) {
        final DeclarationCommand declarationCommand = new DeclarationCommand();
        declarationCommand.setDate(declaration.getLocalDate());
        declarationCommand.setId(declaration.getId());
        declarationCommand.setStatus(declaration.getStatus());
        declarationCommand.setTime(declaration.getLocalTime());
        declarationCommand.setRoom(declaration.getRoom());
        if(declarationCommand.getDocumentCommands()!=null) {
            for (Document document : declaration.getDocuments())
                declarationCommand.getDocumentCommands().add(documentToDocumentCommand.convert(document));
        }
        return declarationCommand;
    }
}
