package com.crio.jukebox.commands;

import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

public class DeletePlaylistCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public DeletePlaylistCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Insufficient arguments for DELETE-PLAYLIST command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int playlistId = Integer.parseInt(args[1]);
        dataRepository.deletePlaylist(userId, playlistId);
        System.out.println("Playlist deleted: " + playlistId);
    }
}
