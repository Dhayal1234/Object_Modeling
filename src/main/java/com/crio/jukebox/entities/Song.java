package com.crio.jukebox.entities;

import java.util.List;

public class Song {
    private int id;
    private String name;
    private String genre;
    private String album;
    private String artist;
    private List<String> featuring;

    public Song(int id, String name, String genre, String album, String artist, List<String> featuring) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.album = album;
        this.artist = artist;
        this.featuring = featuring;
    }

    public Song(int id2, String name2, String artist2, String album2) 
    {
        this.id = id;
        this.name = name;
        this.album = album2;
        this.artist = artist2;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getFeaturing() {
        return featuring;
    }

    public void setFeaturing(List<String> featuring) {
        this.featuring = featuring;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                ", featuring=" + featuring +
                '}';
    }
}
