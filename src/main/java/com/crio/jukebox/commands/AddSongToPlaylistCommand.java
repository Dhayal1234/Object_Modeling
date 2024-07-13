package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class AddSongToPlaylistCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public AddSongToPlaylistCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Insufficient arguments for MODIFY-PLAYLIST ADD-SONG command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int playlistId = Integer.parseInt(args[1]);
        int songId = Integer.parseInt(args[2]);
        dataRepository.addSongToPlaylist(userId, playlistId, songId);
        System.out.println("Song added to playlist: " + songId);
    }
}
