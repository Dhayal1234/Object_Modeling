package com.crio.jukebox.entities;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private List<Playlist> playlists;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.playlists = new ArrayList<>();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", playlists=" + playlists +
                '}';
    }
}
