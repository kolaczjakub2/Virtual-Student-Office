package com.example.demo.Bootstrap;

import com.example.demo.Domain.Declaration;
import com.example.demo.Domain.Term;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DeclarationBootstrap {
    public static List<Declaration> createDeclaration(List<Term> terms) {
        List<Declaration> declarations = new ArrayList<>();
        for (Term term : terms) {
            LocalTime time = term.getStartTime();
            while (time.isBefore(term.getEndTime())) {
                declarations.add(new Declaration("Free", term.getDate(), time, term.getRoom()));
                time = time.plusMinutes(term.getInterval());
            }
        }
        return declarations;
    }
}
