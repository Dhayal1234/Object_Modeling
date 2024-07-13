package com.crio.jukebox.commands;

import java.util.HashMap;
import java.util.Map;

public class CommandInvoker {
    private Map<String, Command> commandMap;

    public CommandInvoker() {
        this.commandMap = new HashMap<>();
    }

    public void registerCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String commandLine) {
        String[] commandParts = commandLine.split(" ", 2);
        String commandName = commandParts[0];
        String[] commandArgs = commandParts.length > 1 ? commandParts[1].split(" ") : new String[]{};

        Command command = commandMap.get(commandName);
        if (command == null) {
            System.out.println("Invalid command: " + commandName);
            return;
        }

        command.execute(commandArgs);
    }
}
