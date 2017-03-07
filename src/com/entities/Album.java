package com.entities;

public class Album {
    private int albumID;
    private String albumName;
    private int yearRel;

    public Album() {}

    public Album(String albumName, int yearRel) {
        this.albumName = albumName;
        this.yearRel = yearRel;
    }

    public int getAlbumID() { return albumID; }
    public String getAlbumName() { return albumName; }
    public int getYearRel() { return yearRel; }
    public void setAlbumID(int albumID) { this.albumID = albumID; }
    public void setAlbumName(String albumName) { this.albumName = albumName; }
    public void setYearRel(int yearRel) { this.yearRel = yearRel; }
}

