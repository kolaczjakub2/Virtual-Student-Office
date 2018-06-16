package com.example.demo.Bootstrap;

import com.example.demo.Commands.AddressCommand;
import com.example.demo.Domain.Address;
import com.example.demo.Domain.Declaration;
import com.example.demo.Domain.Term;
import com.example.demo.Domain.User;
import com.example.demo.Repositories.DeclarationRepository;
import com.example.demo.Repositories.TermRepository;
import com.example.demo.Repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final TermRepository termRepository;
    private final DeclarationRepository declarationRepository;

    public Bootstrap(UserRepository userRepository, TermRepository termRepository, DeclarationRepository declarationRepository) {
        this.userRepository = userRepository;
        this.termRepository = termRepository;
        this.declarationRepository = declarationRepository;
    }

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        List<User> users = (List<User>) userRepository.saveAll(UserBootstrap.createStudents());
        termRepository.saveAll(TermBootstrap.createTerms());
        List<Declaration> declarations = (List<Declaration>) declarationRepository.saveAll(DeclarationBootstrap.createDeclaration(TermBootstrap.createTerms()));
        Random random = new Random();
        for (User user : users) {
            int randInt = random.nextInt(declarations.size());
            while (!declarations.get(randInt).getStatus().equals("Free")) {
                randInt = random.nextInt(declarations.size());
            }
            user.setDeclaration(declarations.get(randInt));
            declarations.get(randInt).setStatus("NotFree");
        }
        userRepository.saveAll(users);

    }
}
