package com.crio.jukebox;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.SongRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoadDataFromSong {
    private SongRepository songRepository;

    public LoadDataFromSong(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public void loadSongs(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String artist = values[2];
                String album = values[3];
                Song song = new Song(id, name, artist, album);
                songRepository.addSong(song);
            }
        } catch (IOException e) {
            System.out.println("Error loading songs: " + e.getMessage());
        }
    }
}
