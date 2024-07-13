package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class PlayNextSongCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public PlayNextSongCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Insufficient arguments for PLAY-SONG NEXT command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        dataRepository.playNextSong(userId);
        System.out.println("Playing next song for user: " + userId);
    }
}
