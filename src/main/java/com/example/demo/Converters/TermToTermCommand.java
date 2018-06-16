package com.example.demo.Converters;

import com.example.demo.Commands.TermCommand;
import com.example.demo.Domain.Term;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TermToTermCommand implements Converter<Term, TermCommand> {

    @Synchronized
    @Nullable
    @Override
    public TermCommand convert(Term term) {
        if (term == null) {
            return null;
        }
        final TermCommand termCommand=new TermCommand();
        termCommand.setDate(term.getDate());
        termCommand.setEndTime(term.getEndTime());
        termCommand.setStartTime(term.getStartTime());
        termCommand.setInterval(term.getInterval());
        termCommand.setRoom(term.getRoom());
        termCommand.setId(term.getId());
        return termCommand;
    }
}
