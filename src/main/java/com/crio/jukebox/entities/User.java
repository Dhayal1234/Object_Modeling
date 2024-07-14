package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private int id;
    private String name;
    private Map<Integer, Playlist> playlists;
    private Playlist currentPlaylist;
    private int currentSongIndex;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.playlists = new HashMap<>();
        this.currentPlaylist = null;
        this.currentSongIndex = -1;
    }

    public Map<Integer, Playlist> getPlaylists() {
        return playlists;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }

    public Integer getId() {
        return id;
    }
}
