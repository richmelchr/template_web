package com.entities;

import com.util.Table;

public class Artist extends Table {
    private int artistID;
    private String artistName;

    public Artist() {}

    public Artist(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistID() { return artistID; }
    public String getArtistName() { return artistName; }
    public void setArtistID(int artistID) { this.artistID = artistID; }
    public void setArtistName(String artistName) { this.artistName = artistName; }
}
