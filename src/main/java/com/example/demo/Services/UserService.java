package com.example.demo.Services;

import com.example.demo.Commands.UserCommand;

import java.util.Set;

public interface UserService {
    UserCommand save(UserCommand userCommand);

    UserCommand login(UserCommand user);

    UserCommand getUser(Long id);

    Set<UserCommand> getUsersWithDeclarations();
}
