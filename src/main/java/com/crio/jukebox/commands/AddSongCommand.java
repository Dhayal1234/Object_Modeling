package com.crio.jukebox.commands;

import com.crio.jukebox.entities.Song;
import com.crio.jukebox.repositories.SongRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddSongCommand implements Command {
    private SongRepository songRepository;

    public AddSongCommand(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public void execute(String[] commandArgs) {
        // Expected commandArgs format: [id, name, genre, album, artist, featuring]
        if (commandArgs.length < 5) {
            System.out.println("Invalid arguments for AddSongCommand");
            return;
        }

        try {
            int id = Integer.parseInt(commandArgs[0]);
            String name = commandArgs[1];
            String genre = commandArgs[2];
            String album = commandArgs[3];
            String artist = commandArgs[4];
            List<String> featuring = new ArrayList<>();

            if (commandArgs.length > 5) {
                featuring = Arrays.asList(Arrays.copyOfRange(commandArgs, 5, commandArgs.length));
            }

            Song song = new Song(id, name, genre, album, artist, featuring);
            songRepository.addSong(song);

            System.out.println("Song added successfully: " + song);
        } catch (NumberFormatException e) {
            System.out.println("Invalid song id: " + commandArgs[0]);
        }
    }
}
