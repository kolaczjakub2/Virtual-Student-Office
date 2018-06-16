package com.example.demo.Converters;

import com.example.demo.Commands.TermCommand;
import com.example.demo.Domain.Term;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class TermCommandToTerm implements Converter<TermCommand, Term> {

    @Synchronized
    @Nullable
    @Override
    public Term convert(TermCommand termCommand) {
        if (termCommand == null) {
            return null;
        }
        final Term term=new Term();
        term.setDate(termCommand.getDate());
        term.setEndTime(termCommand.getEndTime());
        term.setStartTime(termCommand.getStartTime());
        term.setInterval(termCommand.getInterval());
        term.setRoom(termCommand.getRoom());
        term.setId(termCommand.getId());
        return term;
    }
}
