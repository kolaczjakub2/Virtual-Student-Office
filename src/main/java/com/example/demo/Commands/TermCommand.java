package com.example.demo.Commands;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class TermCommand {

    public Long Id;
    public String Room;
    public Integer Interval;
    public LocalDate Date;
    public LocalTime StartTime;
    public LocalTime EndTime;
}
