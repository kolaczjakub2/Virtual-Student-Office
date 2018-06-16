package com.example.demo.PDFGenerator;

import com.example.demo.Domain.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PDFGenerator {

    private static PDFGenerator instance;

    private PDFGenerator() {
    }

    public static PDFGenerator getInstance() {
        if (instance == null)
            instance = new PDFGenerator();
        return instance;
    }

    public void generateDocument(List<User> users, ArrayList<String> selectedRooms, ArrayList<LocalDate> selectedDates) {

        Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("Deklaracje.pdf"));
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Font roomFont = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font dateFont = FontFactory.getFont(FontFactory.COURIER, 13, BaseColor.BLACK);
        document.open();
        for (String room : selectedRooms) {
            for (LocalDate date : selectedDates.stream().sorted(LocalDate::compareTo).collect(Collectors.toList())) {
                List<User> userList = users.stream()
                        .filter(user -> user.getDeclaration().getRoom().equals(room) && user.getDeclaration().getLocalDate().equals(date))
                        .sorted(Comparator.comparing(user1 -> user1.getDeclaration().getLocalTime())).collect(Collectors.toList());
                if (userList.size() < 1)
                    continue;
                Chunk roomChunk = new Chunk(room + "\n", roomFont);
                Chunk dateChunk = new Chunk(date.toString(), dateFont);
                try {
                    document.add(new Paragraph(roomChunk));
                    document.add(new Paragraph(dateChunk));
                    document.add(Chunk.NEWLINE);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                PdfPTable table = new PdfPTable(3);
                addTableHeader(table);

                userList.forEach(user -> addRows(table, user));
                try {
                    document.add(table);
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }
        document.close();
    }


    private void addTableHeader(PdfPTable table) {
        Stream.of("Imie", "Nazwisko", "Godzina")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, User user) {
        table.addCell(user.getFirstName());
        table.addCell(user.getLastName());
        table.addCell(user.getDeclaration().getLocalTime().toString());

    }

}
