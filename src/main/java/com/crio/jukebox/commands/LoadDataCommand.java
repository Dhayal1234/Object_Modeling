package com.crio.jukebox.commands;

import com.crio.jukebox.LoadDataFromSong;
import com.crio.jukebox.repositories.SongRepository;

public class LoadDataCommand implements Command {
    private LoadDataFromSong loadDataFromSong;

    public LoadDataCommand(SongRepository songRepository) {
        this.loadDataFromSong = new LoadDataFromSong(songRepository);
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Insufficient arguments for LOAD-DATA command.");
            return;
        }
        String csvFilePath = args[0];
        loadDataFromSong.loadSongs(csvFilePath);
        System.out.println("Song data loaded successfully from " + csvFilePath);
    }
}
