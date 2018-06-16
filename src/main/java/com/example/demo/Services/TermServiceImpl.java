package com.example.demo.Services;

import com.example.demo.Commands.TermCommand;
import com.example.demo.Converters.TermCommandToTerm;
import com.example.demo.Converters.TermToTermCommand;
import com.example.demo.Domain.Declaration;
import com.example.demo.Domain.Term;
import com.example.demo.Domain.User;
import com.example.demo.Repositories.DeclarationRepository;
import com.example.demo.Repositories.TermRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalTime;
import java.util.*;

@Service
public class TermServiceImpl implements TermService {

    private final DeclarationRepository declarationRepository;
    private final TermRepository termRepository;
    private final TermToTermCommand termToTermCommand;
    private final TermCommandToTerm termCommandToTerm;
    private final UserRepository userRepository;

    public TermServiceImpl(TermRepository termRepository,
                           TermToTermCommand termToTermCommand,
                           TermCommandToTerm termCommandToTerm,
                           DeclarationRepository declarationRepository,
                           UserRepository userRepository) {
        this.termRepository = termRepository;
        this.termToTermCommand = termToTermCommand;
        this.termCommandToTerm = termCommandToTerm;
        this.declarationRepository = declarationRepository;
        this.userRepository = userRepository;
    }


    @Override
    public TermCommand getTerm(Long Id) {
        Optional<Term> optionalTerm = termRepository.findById(Id);
        if (optionalTerm.isPresent()) {
            Term term = optionalTerm.get();
            return termToTermCommand.convert(term);
        }
        throw new RuntimeException("Cannot find a term");
    }

    @Override
    public Set<TermCommand> getAllTerms() {
        Set<Term> termSet = new HashSet<>();
        Set<TermCommand> termCommandSet = new HashSet<>();
        termRepository.findAll().iterator().forEachRemaining(termSet::add);
        for (Term term : termSet)
            termCommandSet.add(termToTermCommand.convert(term));

        return termCommandSet;
    }

    @Override
    public TermCommand saveTerm(TermCommand termCommand) {
        Term term = termCommandToTerm.convert(termCommand);
        if (term.getStartTime().isAfter(term.getEndTime()))
            throw new RuntimeException("Start time must be after than End time");
        Term savedTerm = termRepository.save(term);
        declarationRepository.saveAll(createDeclarations(savedTerm.getStartTime(), savedTerm));
        return termToTermCommand.convert(savedTerm);
    }

    @Override
    public TermCommand deleteTerm(Long Id) {
        Optional<Term> optionalDeletedTerm = termRepository.findById(Id);
        if (optionalDeletedTerm.isPresent()) {
            Term deletedTerm = optionalDeletedTerm.get();
            termRepository.delete(deletedTerm);

            LocalTime time = deletedTerm.getStartTime();
            declarationRepository.deleteAll(deleteDeclarations(time, deletedTerm));
            return termToTermCommand.convert(deletedTerm);
        }

        throw new RuntimeException("Term already didn't exist");
    }

    public List<Declaration> createDeclarations(LocalTime time, Term savedTerm) {
        List<Declaration> declarationList = new ArrayList<>();
        while (time.isBefore(savedTerm.getEndTime())) {
            Declaration declaration = new Declaration();
            declaration.setLocalDate(savedTerm.getDate());
            declaration.setStatus("Free");
            declaration.setLocalTime(time);
            declaration.setRoom(savedTerm.getRoom());
            declarationList.add(declaration);
            time = time.plusMinutes(savedTerm.Interval);
        }
        return declarationList;
    }

    public List<Declaration> deleteDeclarations(LocalTime time, Term deletedTerm) {
        List<Declaration> declarationList = new ArrayList<>();
        while (time.isBefore(deletedTerm.getEndTime())) {
            Optional<Declaration> optionalDeclaration = declarationRepository.getDeclarationByTimeAndRoom(deletedTerm.getRoom(), deletedTerm.getDate(), time);
            if (optionalDeclaration.isPresent()) {
                Declaration declaration = optionalDeclaration.get();
                Optional<User> optionalUser = userRepository.findByDeclaration(declaration);
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    notifyUser(user);
                    user.setDeclaration(null);
                    userRepository.save(user);
                }
                declarationList.add(declaration);
            }
            time = time.plusMinutes(deletedTerm.Interval);
        }

        return declarationList;
    }

    private void notifyUser(User user) {
        final String username = "kolacz.jakub2@gmail.com";
        final String password = "madafaka12";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session=Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try{
            Message message=new MimeMessage(session);
            message.setFrom(new InternetAddress((username)));//from
            message.setRecipient(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail())[0]);//to
            message.setSubject("Usunięcie wybranego terminu");
            message.setText("Szanowny Studencie, \n Termin wybrany przez Ciebie został usunięty." +
                    "Przepraszamy za utrudnienia i prosimy o ponowne dokonanie wyboru\n" +
                    "Z wyrazami szacunku \n Pracownik Sekretariatu");

            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
