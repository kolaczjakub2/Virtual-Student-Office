package com.example.demo.Domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long Id;
    public String Room;
    public Integer Interval;
    public LocalDate Date;
    public LocalTime StartTime;
    public LocalTime EndTime;

    public Term() {
    }

    public Term(String room, Integer interval, LocalDate date, LocalTime startTime, LocalTime endTime) {
        Room = room;
        Interval = interval;
        Date = date;
        StartTime = startTime;
        EndTime = endTime;
    }
}
