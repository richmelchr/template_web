package com.entities;

import com.util.Table;

public class Playlist extends Table {
    public int playlistID;
    public String playlistName;
    public int playlistNumber;
    public int test;

    public Playlist() {}

    public Playlist(int playlistNumber, String playlistName, int test) {
        this.playlistNumber = playlistNumber;
        this.playlistName = playlistName;
        this.test = test;
    }

    public void setPlaylistNumber(int playlistNumber) { this.playlistNumber = playlistNumber;}
    public void setPlaylistName(String playlistName) { this.playlistName = playlistName; }
    public void setTest(int test) { this.test = test; }

    public int getPlaylistID() {return playlistID; }
    public int getPlaylistNumber() { return playlistNumber; }
    public String getPlaylistName() { return playlistName; }
    public int getTest() { return test; }
}
