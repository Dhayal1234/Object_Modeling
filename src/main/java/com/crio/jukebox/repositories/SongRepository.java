package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Song;

import java.util.List;

public interface SongRepository {
    void addSong(Song song);
    Song getSong(int id);
    List<Song> getAllSongs();
}
