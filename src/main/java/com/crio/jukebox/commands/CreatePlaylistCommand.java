package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class CreatePlaylistCommand implements Command {
    private JukeboxDataRepositoryImpl dataRepository;

    public CreatePlaylistCommand(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 4) {
            System.out.println("Insufficient arguments for CREATE-PLAYLIST command.");
            return;
        }
        int userId = Integer.parseInt(args[0]);
        int playlistId = Integer.parseInt(args[1]);
        String playlistName = args[2];
        List<Integer> songIds = new ArrayList<>();
        for (int i = 3; i < args.length; i++) {
            songIds.add(Integer.parseInt(args[i]));
        }
        dataRepository.createPlaylist(userId, playlistId, playlistName, songIds);
        System.out.println("Playlist created: " + playlistName);
    }
}
