package com.crio.jukebox.commands;

import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.UserRepository;

public class CreateUserCommand implements Command {
    private UserRepository userRepository;

    public CreateUserCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments for CREATE-USER command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        String userName = args[1];
        userRepository.addUser(new User(userId, userName));
        System.out.println("User created: " + userName);
    }
}
