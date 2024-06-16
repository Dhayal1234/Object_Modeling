package com.crio.codingame.commands;

import java.util.List;

import com.crio.codingame.entities.Contest;
import com.crio.codingame.entities.Level;
import com.crio.codingame.services.IContestService;

public class ListContestCommand implements ICommand{

    private final IContestService contestService;
    
    public ListContestCommand(IContestService contestService) {
        this.contestService = contestService;
    }

    // TODO: CRIO_TASK_MODULE_CONTROLLER
    // Execute getAllContestLevelWise method of IContestService and print the result.
    // Look for the unit tests to see the expected output.
    // Sample Input Token List:- ["LIST_CONTEST","HIGH"]
    // or
    // ["LIST_CONTEST"]

    @Override
    public void execute(List<String> tokens) {
        if (tokens.size() == 1) {
            // Case: ["LIST_CONTEST"]
            List<Contest> contests = contestService.getAllContestLevelWise(null);
            printContestList(contests);
        } else if (tokens.size() == 2) {
            // Case: ["LIST_CONTEST", "HIGH"] or ["LIST_CONTEST", "MEDIUM"]
            Level level = Level.valueOf(tokens.get(1));
            List<Contest> contests = contestService.getAllContestLevelWise(level);
            printContestList(contests);
        } else {
            throw new IllegalArgumentException("Invalid tokens provided for listing contests");
        }
    }
    private void printContestList(List<Contest> contests) {
        System.out.print("[");
        for (int i = 0; i < contests.size(); i++) {
            Contest contest = contests.get(i);
            System.out.print(contest.toString());
            if (i < contests.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
}
