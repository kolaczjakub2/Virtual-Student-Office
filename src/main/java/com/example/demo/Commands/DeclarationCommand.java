package com.example.demo.Commands;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
public class DeclarationCommand {

    private Long Id;
    private String Status;
    private LocalDate Date;
    private LocalTime Time;
    private String Room;
    private Set<DocumentCommand> documentCommands;
}
