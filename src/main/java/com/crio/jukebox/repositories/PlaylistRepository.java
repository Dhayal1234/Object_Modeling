package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Playlist;

import java.util.List;

public interface PlaylistRepository {
    void addPlaylist(Playlist playlist);
    Playlist getPlaylist(int id);
    void removePlaylist(int id);

    // Additional methods for playlist management
    void createPlaylist(int userId, int playlistId, String playlistName, List<Integer> songIds);
    void deletePlaylist(int userId, int playlistId);
    void addSongToPlaylist(int userId, int playlistId, int songId);
    void removeSongFromPlaylist(int userId, int playlistId, int songId);
    void playPlaylist(int userId, int playlistId);
    void playSong(int userId, int songId);
    void playNextSong(int userId);
    void playPreviousSong(int userId);
}
