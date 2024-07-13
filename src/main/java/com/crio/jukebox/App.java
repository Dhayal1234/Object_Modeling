package com.crio.jukebox;

import com.crio.jukebox.commands.*;
import com.crio.jukebox.repositories.*;
import com.crio.jukebox.services.JukeboxService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
        else {
            System.out.println("Invalid command line arguments. Please provide INPUT_FILE=<filename>");
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.
    String fileName = commandLineArgs.get(0).split("=")[1];

    // Initialize repositories and services
    JukeboxDataRepositoryImpl dataRepository = new JukeboxDataRepositoryImpl();
    JukeboxService jukeboxService = new JukeboxService(dataRepository);

    // Read commands from file and process each command
    List<String> commands = readCommandsFromFile(fileName);
    for (String command : commands) {
        jukeboxService.processCommand(command);
    }            

    }


    private static List<String> readCommandsFromFile(String fileName) {
        List<String> commands = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    commands.add(line.trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
        return commands;
    }

}


