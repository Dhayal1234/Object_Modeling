package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class PlaySongCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public PlaySongCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments for PLAY-SONG command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int songId = Integer.parseInt(args[1]);
        dataRepository.playSong(userId, songId);
        System.out.println("Playing song: " + songId);
    }
}
