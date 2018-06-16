package com.example.demo.Commands;

import lombok.Data;

@Data
public class DocumentCommand {

    private Long Id;
    private String DisplayName;
    private String Data;
    private String FileName;
    private String FileType;
}
