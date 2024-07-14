package com.crio.jukebox.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

public class JukeboxService {
    private Map<Integer, User> users;
    private Map<Integer, Song> songs;

    public JukeboxService() {
        this.users = new HashMap<>();
        this.songs = new HashMap<>();
    }

    public void processCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0]) {
            case "LOAD-DATA":
                loadData(parts[1]);
                break;
            case "CREATE-USER":
                createUser(parts[1]);
                break;
            case "CREATE-PLAYLIST":
                createPlaylist(Integer.parseInt(parts[1]), parts[2], Arrays.copyOfRange(parts, 3, parts.length));
                break;
            case "DELETE-PLAYLIST":
                deletePlaylist(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                break;
            case "PLAY-PLAYLIST":
                playPlaylist(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                break;
            case "MODIFY-PLAYLIST":
                if (parts[1].equals("ADD-SONG")) {
                    addSongToPlaylist(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                } else if (parts[1].equals("DELETE-SONG")) {
                    deleteSongFromPlaylist(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                }
                break;
            case "PLAY-SONG":
                if (parts[2].equals("NEXT")) {
                    playNextSong(Integer.parseInt(parts[1]));
                } else if (parts[2].equals("BACK")) {
                    playPreviousSong(Integer.parseInt(parts[1]));
                } else {
                    playSong(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                }
                break;
            default:
                System.out.println("Unknown command: " + command);
        }
    }

    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) continue; // Skip lines with insufficient data
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String genre = parts[2];
                String album = parts[3];
                String artist = parts[4];
                String featuring = parts.length > 5 ? parts[5] : ""; // Handle missing featuring field
                Song song = new Song(id, name, genre, album, artist, featuring);
                songs.put(id, song);
            }
            System.out.println("Songs Loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createUser(String name) {
        int id = users.size() + 1;
        User user = new User(id, name);
        users.put(id, user);
        System.out.println(user);
    }

    public void createPlaylist(int userId, String playlistName, String[] songIds) {
        User user = users.get(userId);
        if (user != null) {
            int playlistId = user.getPlaylists().size() + 1;
            Playlist playlist = new Playlist(playlistId, playlistName);
            for (String songId : songIds) {
                Song song = songs.get(Integer.parseInt(songId));
                if (song != null) {
                    playlist.addSong(song);
                }
            }
            user.getPlaylists().put(playlistId, playlist);
            System.out.println("Playlist ID - " + playlistId);
        }
    }

    public void deletePlaylist(int userId, int playlistId) {
        User user = users.get(userId);
        if (user != null) {
            user.getPlaylists().remove(playlistId);
            System.out.println("Delete Successful");
        }
    }

    public void playPlaylist(int userId, int playlistId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = user.getPlaylists().get(playlistId);
            if (playlist != null) {
                user.setCurrentPlaylist(playlist);
                user.setCurrentSongIndex(0);
                playCurrentSong(user);
            }
        }
    }

    public void addSongToPlaylist(int userId, int playlistId, int songId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = user.getPlaylists().get(playlistId);
            Song song = songs.get(songId);
            if (playlist != null && song != null) {
                playlist.addSong(song);
                System.out.println(playlist);
            }
        }
    }

    public void deleteSongFromPlaylist(int userId, int playlistId, int songId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = user.getPlaylists().get(playlistId);
            Song song = songs.get(songId);
            if (playlist != null && song != null) {
                playlist.removeSong(song);
                System.out.println(playlist);
            }
        }
    }

    public void playSong(int userId, int songId) {
        User user = users.get(userId);
        if (user != null && user.getCurrentPlaylist() != null) {
            Song song = songs.get(songId);
            if (song != null && user.getCurrentPlaylist().getSongs().contains(song)) {
                user.setCurrentSongIndex(user.getCurrentPlaylist().getSongs().indexOf(song));
                playCurrentSong(user);
            } else {
                System.out.println("Given song id is not a part of the active playlist");
            }
        }
    }

    public void playNextSong(int userId) {
        User user = users.get(userId);
        if (user != null && user.getCurrentPlaylist() != null) {
            int nextIndex = user.getCurrentSongIndex() + 1;
            if (nextIndex < user.getCurrentPlaylist().getSongs().size()) {
                user.setCurrentSongIndex(nextIndex);
            } else {
                user.setCurrentSongIndex(0);
            }
            playCurrentSong(user);
        }
    }

    public void playPreviousSong(int userId) {
        User user = users.get(userId);
        if (user != null && user.getCurrentPlaylist() != null) {
            int prevIndex = user.getCurrentSongIndex() - 1;
            if (prevIndex >= 0) {
                user.setCurrentSongIndex(prevIndex);
            } else {
                user.setCurrentSongIndex(user.getCurrentPlaylist().getSongs().size() - 1);
            }
            playCurrentSong(user);
        }
    }

    private void playCurrentSong(User user) {
        if (user.getCurrentPlaylist() != null && user.getCurrentSongIndex() >= 0) {
            Song song = user.getCurrentPlaylist().getSongs().get(user.getCurrentSongIndex());
            System.out.println("Current Song Playing\n" + song);
        }
    }
}
