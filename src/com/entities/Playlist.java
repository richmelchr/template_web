package com.entities;

import com.util.Table;

public class Playlist extends Table {
    public int playlistID;
    public String playlistName;
    public int playlistNumber;

    public Playlist() {}

    public Playlist(int playlistNumber, String playlistName) {
        this.playlistNumber = playlistNumber;
        this.playlistName = playlistName;
    }

    public void setPlaylistNumber(int playlistNumber) { this.playlistNumber = playlistNumber;}
    public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }

    public int getPlaylistID() {return playlistID; }
    public int getPlaylistNumber() { return playlistNumber; }
    public String getPlaylistName() { return playlistName; }
}
