package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.Playlist;
import com.crio.jukebox.entities.Song;
import com.crio.jukebox.entities.User;

import java.util.*;

public class JukeboxDataRepositoryImpl implements SongRepository, UserRepository, PlaylistRepository {
    private Map<Integer, Song> songs;
    private Map<Integer, User> users;
    private Map<Integer, Playlist> playlists;

    public JukeboxDataRepositoryImpl() {
        this.songs = new HashMap<>();
        this.users = new HashMap<>();
        this.playlists = new HashMap<>();
    }

    // SongRepository methods
    @Override
    public void addSong(Song song) {
        songs.put(song.getId(), song);
    }

    @Override
    public Song getSong(int id) {
        return songs.get(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return new ArrayList<>(songs.values());
    }

    // UserRepository methods
    @Override
    public void addUser(User user) {
        //users.put(user.getId(), user);
    }

    @Override
    public User getUser(int id) {
        return users.get(id);
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    // PlaylistRepository methods
    @Override
    public void addPlaylist(Playlist playlist) {
        playlists.put(playlist.getId(), playlist);
    }

    @Override
    public Playlist getPlaylist(int id) {
        return playlists.get(id);
    }

    @Override
    public void removePlaylist(int id) {
        playlists.remove(id);
    }

    @Override
    public void createPlaylist(int userId, int playlistId, String playlistName, List<Integer> songIds) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = new Playlist(playlistId, playlistName);
            for (int songId : songIds) {
                Song song = songs.get(songId);
                if (song != null) {
                    playlist.getSongs().add(song);
                }
            }
            //user.getPlaylists().add(playlist);
            addPlaylist(playlist);
        }
    }

    @Override
    public void deletePlaylist(int userId, int playlistId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = getPlaylist(playlistId);
            if (playlist != null) {
                user.getPlaylists().remove(playlist);
                removePlaylist(playlistId);
            }
        }
    }

    @Override
    public void addSongToPlaylist(int userId, int playlistId, int songId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = getPlaylist(playlistId);
            Song song = songs.get(songId);
            if (playlist != null && song != null) {
                playlist.getSongs().add(song);
            }
        }
    }

    @Override
    public void removeSongFromPlaylist(int userId, int playlistId, int songId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = getPlaylist(playlistId);
            Song song = songs.get(songId);
            if (playlist != null && song != null) {
                playlist.getSongs().remove(song);
            }
        }
    }

    @Override
    public void playPlaylist(int userId, int playlistId) {
        User user = users.get(userId);
        if (user != null) {
            Playlist playlist = getPlaylist(playlistId);
            if (playlist != null) {
                // Logic to play the playlist
                System.out.println("Playing playlist: " + playlist.getName());
                for (Song song : playlist.getSongs()) {
                    System.out.println("Playing song: " + song.getName());
                }
            }
        }
    }

    @Override
    public void playSong(int userId, int songId) {
        User user = users.get(userId);
        if (user != null) {
            Song song = songs.get(songId);
            if (song != null) {
                // Logic to play the song
                System.out.println("Playing song: " + song.getName());
            }
        }
    }

    @Override
    public void playNextSong(int userId) {
        User user = users.get(userId);
        if (user != null) {
            // List<Playlist> playlists = user.getPlaylists();
            // for (Playlist playlist : playlists) {
            //     // Logic to play the next song
            //     List<Song> songs = playlist.getSongs();
            //     if (!songs.isEmpty()) {
            //         Song nextSong = songs.get(1); // Assuming the next song is the second song in the list
            //         System.out.println("Playing next song: " + nextSong.getName());
            //         break;
            //     }
            // }
        }
    }

    @Override
    public void playPreviousSong(int userId) {
        User user = users.get(userId);
        if (user != null) {
            // List<Playlist> playlists = user.getPlaylists();
            // for (Playlist playlist : playlists) {
            //     // Logic to play the previous song
            //     List<Song> songs = playlist.getSongs();
            //     if (!songs.isEmpty()) {
            //         Song previousSong = songs.get(songs.size() - 1); // Assuming the previous song is the last song in the list
            //         System.out.println("Playing previous song: " + previousSong.getName());
            //         break;
            //     }
            // }
        }
    }
}
