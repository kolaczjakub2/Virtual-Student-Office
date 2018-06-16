package com.example.demo.Converters;

import com.example.demo.Commands.DocumentCommand;
import com.example.demo.Domain.Document;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentCommandToDocument implements Converter<DocumentCommand, Document> {
    @Override
    public Document convert(DocumentCommand documentCommand) {
        final Document document = new Document();
        document.setData(documentCommand.getData());
        document.setDisplayName(documentCommand.getDisplayName());
        document.setId(documentCommand.getId());
        document.setFileName(documentCommand.getFileName());
        document.setFileType(documentCommand.getFileType());
        return document;
    }
}
