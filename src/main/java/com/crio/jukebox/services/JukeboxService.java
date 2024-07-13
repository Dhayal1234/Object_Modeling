package com.crio.jukebox.services;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.JukeboxDataRepositoryImpl;

import java.util.List;
import java.util.stream.Collectors;

public class JukeboxService {
    private JukeboxDataRepositoryImpl dataRepository;

    public JukeboxService(JukeboxDataRepositoryImpl dataRepository) {
        this.dataRepository = dataRepository;
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        String action = parts[0];
        switch (action) {
            case "ADD_SONG":
                int songId = Integer.parseInt(parts[1]);
                String songName = parts[2];
                String artist = parts[3];
                String album = parts[4];
                Song song = new Song(songId, songName, artist, album);
                addSong(song);
                break;
            case "CREATE_USER":
                int userId = Integer.parseInt(parts[1]);
                String userName = parts[2];
                createUser(userId, userName);
                break;
            case "CREATE_PLAYLIST":
                int creatorId = Integer.parseInt(parts[1]);
                int playlistId = Integer.parseInt(parts[2]);
                String playlistName = parts[3];
                List<Integer> songIds = List.of(parts[4].split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
                createPlaylist(creatorId, playlistId, playlistName, songIds);
                break;
            case "DELETE_PLAYLIST":
                int userIdToDeletePlaylist = Integer.parseInt(parts[1]);
                int playlistIdToDelete = Integer.parseInt(parts[2]);
                deletePlaylist(userIdToDeletePlaylist, playlistIdToDelete);
                break;
            case "PLAY_PLAYLIST":
                int userIdToPlayPlaylist = Integer.parseInt(parts[1]);
                int playlistIdToPlay = Integer.parseInt(parts[2]);
                playPlaylist(userIdToPlayPlaylist, playlistIdToPlay);
                break;
            case "ADD_SONG_TO_PLAYLIST":
                int userIdToAddSong = Integer.parseInt(parts[1]);
                int playlistIdToAddSong = Integer.parseInt(parts[2]);
                int songIdToAdd = Integer.parseInt(parts[3]);
                addSongToPlaylist(userIdToAddSong, playlistIdToAddSong, songIdToAdd);
                break;
            case "REMOVE_SONG_FROM_PLAYLIST":
                int userIdToRemoveSong = Integer.parseInt(parts[1]);
                int playlistIdToRemoveSong = Integer.parseInt(parts[2]);
                int songIdToRemove = Integer.parseInt(parts[3]);
                removeSongFromPlaylist(userIdToRemoveSong, playlistIdToRemoveSong, songIdToRemove);
                break;
            case "PLAY_SONG":
                int userIdToPlaySong = Integer.parseInt(parts[1]);
                int songIdToPlay = Integer.parseInt(parts[2]);
                playSong(userIdToPlaySong, songIdToPlay);
                break;
            case "PLAY_NEXT_SONG":
                int userIdToPlayNext = Integer.parseInt(parts[1]);
                playNextSong(userIdToPlayNext);
                break;
            case "PLAY_PREVIOUS_SONG":
                int userIdToPlayPrevious = Integer.parseInt(parts[1]);
                playPreviousSong(userIdToPlayPrevious);
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    public void addSong(Song song) {
        dataRepository.addSong(song);
    }

    public void createUser(int id, String name) {
        User user = new User(id, name);
        dataRepository.addUser(user);
    }

    public void createPlaylist(int userId, int playlistId, String playlistName, List<Integer> songIds) {
        dataRepository.createPlaylist(userId, playlistId, playlistName, songIds);
    }

    public void deletePlaylist(int userId, int playlistId) {
        dataRepository.deletePlaylist(userId, playlistId);
    }

    public void playPlaylist(int userId, int playlistId) {
        dataRepository.playPlaylist(userId, playlistId);
    }

    public void addSongToPlaylist(int userId, int playlistId, int songId) {
        dataRepository.addSongToPlaylist(userId, playlistId, songId);
    }

    public void removeSongFromPlaylist(int userId, int playlistId, int songId) {
        dataRepository.removeSongFromPlaylist(userId, playlistId, songId);
    }

    public void playSong(int userId, int songId) {
        dataRepository.playSong(userId, songId);
    }

    public void playNextSong(int userId) {
        dataRepository.playNextSong(userId);
    }

    public void playPreviousSong(int userId) {
        dataRepository.playPreviousSong(userId);
    }
}
