package com.example.demo.Services;

import com.example.demo.Commands.UserCommand;
import com.example.demo.Converters.UserCommandToUser;
import com.example.demo.Converters.UserToUserCommand;
import com.example.demo.Domain.User;
import com.example.demo.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserToUserCommand userToUserCommand;
    private final UserCommandToUser userCommandToUser;
    private final UserRepository userRepository;

    public UserServiceImpl(UserToUserCommand userToUserCommand, UserCommandToUser userCommandToUser, UserRepository userRepository) {
        this.userToUserCommand = userToUserCommand;
        this.userCommandToUser = userCommandToUser;
        this.userRepository = userRepository;
    }

    @Override
    public UserCommand save(UserCommand userCommand) {

        User user = userCommandToUser.convert(userCommand);
        Optional<User> optionalUserFromDB = userRepository.findByCardNumber(user.getCardNumber());
        if (optionalUserFromDB.isPresent())
            throw new RuntimeException("User already exist");
        User savedUser = userRepository.save(user);


        return userToUserCommand.convert(savedUser);

    }

    @Override
    public UserCommand login(UserCommand user) {
        Optional<User> optionalUserFromDB = userRepository.findByCardNumber(user.getCardNumber());

        if (optionalUserFromDB.isPresent()) {
            User userFromDB = optionalUserFromDB.get();
            if (userFromDB.getPassword().equals(user.getPassword()))
                return userToUserCommand.convert(userFromDB);
        }
        throw new RuntimeException("Wrong Password or Login");
    }

    @Override
    public UserCommand getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            UserCommand userCommand = userToUserCommand.convert(user);
            return userCommand;
        }
        throw new RuntimeException("User didnt exist");
    }

    @Override
    public Set<UserCommand> getUsersWithDeclarations() {
        Optional<List<User>> optionalUsers = userRepository.findUsersWithDeclarations();
        if (optionalUsers.isPresent()) {
            Set<UserCommand> userCommandSet = new HashSet<>();
            List<User> userList = optionalUsers.get();
            for (User user : userList)
                userCommandSet.add(userToUserCommand.convert(user));
            return userCommandSet;
        }

        throw new RuntimeException("Everyone users didnt have already declarations");
    }
}
