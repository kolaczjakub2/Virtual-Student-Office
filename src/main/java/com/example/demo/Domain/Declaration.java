package com.example.demo.Domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity
public class Declaration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Status;

    private LocalDate localDate;

    private LocalTime localTime;

    private String Room;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Document> documents;

    public Declaration(String status, LocalDate localDate, LocalTime localTime, String room) {
        Status = status;
        this.localDate = localDate;
        this.localTime = localTime;
        Room = room;
    }

    public Declaration() {
    }
}
