package com.entities;

import com.util.Table;

public class Playlist extends Table {
    private int playlistID;
    private String playlistName;
    private int testInt;
    private String testString;

    public Playlist() {}

    public Playlist(int playlistID, String playlistName) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
    }

    public String getPlaylistName() { return playlistName; }
    public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }

    public int getPlaylistID() { return playlistID; }
}
