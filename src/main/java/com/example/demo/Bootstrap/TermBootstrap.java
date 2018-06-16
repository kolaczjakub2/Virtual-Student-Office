package com.example.demo.Bootstrap;

import com.example.demo.Domain.Term;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TermBootstrap {
    public static List<Term> createTerms() {
        List<Term> terms = new ArrayList<>();
        terms.add(new Term("G122", 15, LocalDate.now(), LocalTime.parse("09:00"), LocalTime.parse("15:00")));
        terms.add(new Term("G122", 15, LocalDate.now().plusDays(1), LocalTime.parse("09:00"), LocalTime.parse("15:00")));
        terms.add(new Term("G122", 15, LocalDate.now().plusDays(2), LocalTime.parse("09:00"), LocalTime.parse("15:00")));
        terms.add(new Term("G118", 15, LocalDate.now().plusDays(5), LocalTime.parse("09:00"), LocalTime.parse("15:00")));
        terms.add(new Term("G118", 15, LocalDate.now().plusDays(7), LocalTime.parse("09:00"), LocalTime.parse("15:00")));
        return terms;
    }
}
