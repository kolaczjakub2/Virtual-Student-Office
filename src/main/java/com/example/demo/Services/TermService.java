package com.example.demo.Services;

import com.example.demo.Commands.TermCommand;

import java.util.Set;

public interface TermService {
    TermCommand getTerm(Long Id);

    Set<TermCommand> getAllTerms();

    TermCommand saveTerm(TermCommand termCommand);

    TermCommand deleteTerm(Long Id);
}
