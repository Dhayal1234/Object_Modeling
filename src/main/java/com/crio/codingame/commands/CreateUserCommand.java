package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.User;
import com.crio.codingame.services.IUserService;

public class CreateUserCommand implements ICommand{

    private final IUserService userService;
    
    public CreateUserCommand(IUserService userService) {
        this.userService = userService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IUserService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_QUESTION","Ross"]

    @Override
    public void execute(List<String> tokens) {
        // Assuming tokens = ["CREATE-USER", "name"]
        if (tokens.size() < 2) {
            throw new IllegalArgumentException("Invalid tokens provided for user creation");
        }
        
        // Extract parameters
        String username = tokens.get(1); // Assuming username is the second token
        
        // Execute create method of IUserService
        User createdUser = userService.create(username);
        
        // Print the result (as per test expectation, print User.toString())
        System.out.println(createdUser.toString());
    }
    
}
