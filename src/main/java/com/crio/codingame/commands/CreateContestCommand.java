package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.exceptions.QuestionNotFoundException;
import com.crio.codingame.exceptions.UserNotFoundException;
import com.crio.codingame.services.IContestService;

public class CreateContestCommand implements ICommand{

    private final IContestService contestService;

    public CreateContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute create method of IContestService and print the result.
    // Also Handle Exceptions and print the error messsages if any.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["CREATE_CONTEST","CRIODO2_CONTEST","LOW","Monica","40"]
    // or
    // ["CREATE_CONTEST","CRIODO1_CONTEST","HIGH","Ross"]
    // Hint - Use Parameterized Exceptions in the Service class to match with the Unit Tests Output.

    @Override
    public void execute(List<String> tokens) {
    // Input validation
    if (tokens.size() < 3) {
        throw new IllegalArgumentException("Invalid tokens provided for contest creation");
    }

    try {
        // Extract parameters
        String contestName = tokens.get(1);
        Level level = Level.valueOf(tokens.get(2));
        String creatorName = tokens.get(3);
        Integer numQuestions = tokens.size() > 4 ? Integer.parseInt(tokens.get(4)) : null;

        // Execute create method of IContestService
        Contest createdContest = contestService.create(contestName, level, creatorName, numQuestions);

        // Print the result (as per test expectation, print Contest.toString())
        System.out.println(createdContest.toString());
    } catch (UserNotFoundException | QuestionNotFoundException e) {
        // Exception handling for specific exceptions
        System.out.println(e.getMessage());
    } catch (IllegalArgumentException e) {
        // Exception handling for input validation errors
        System.out.println(e.getMessage());
    }
}
    
}
