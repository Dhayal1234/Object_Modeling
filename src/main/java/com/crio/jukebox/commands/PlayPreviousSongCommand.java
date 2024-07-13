package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class PlayPreviousSongCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public PlayPreviousSongCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Insufficient arguments for PLAY-SONG BACK command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        dataRepository.playPreviousSong(userId);
        System.out.println("Playing previous song for user: " + userId);
    }
}
