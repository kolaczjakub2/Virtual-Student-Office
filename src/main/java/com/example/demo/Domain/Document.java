package com.example.demo.Domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String DisplayName;
    private String FileName;
    private String FileType;
    @Lob
    private String Data;


}
