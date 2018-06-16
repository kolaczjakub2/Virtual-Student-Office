package com.example.demo.Converters;

import com.example.demo.Commands.DocumentCommand;
import com.example.demo.Domain.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentToDocumentCommand implements Converter<Document, DocumentCommand> {
    @Override
    public DocumentCommand convert(Document document) {
        final DocumentCommand documentCommand = new DocumentCommand();
        documentCommand.setData(document.getData());
        documentCommand.setDisplayName(document.getDisplayName());
        documentCommand.setId(document.getId());
        documentCommand.setFileName(document.getFileName());
        documentCommand.setFileType(document.getFileType());
        return documentCommand;
    }
}
