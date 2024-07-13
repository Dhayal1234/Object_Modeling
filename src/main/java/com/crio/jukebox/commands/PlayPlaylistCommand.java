package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class PlayPlaylistCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public PlayPlaylistCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments for PLAY-PLAYLIST command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int playlistId = Integer.parseInt(args[1]);
        dataRepository.playPlaylist(userId, playlistId);
        System.out.println("Playing playlist: " + playlistId);
    }
}
