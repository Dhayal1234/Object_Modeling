package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class RemoveSongFromPlaylistCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public RemoveSongFromPlaylistCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Insufficient arguments for MODIFY-PLAYLIST DELETE-SONG command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int playlistId = Integer.parseInt(args[1]);
        int songId = Integer.parseInt(args[2]);
        dataRepository.removeSongFromPlaylist(userId, playlistId, songId);
        System.out.println("Song removed from playlist: " + songId);
    }
}
