package com.entities;

public class Song {
    private int songID;
    private String songName;
    private int yearWritten;
    private int length;
    private String genre;
    private int writerID;
    private int artistID;
    private int albumID;

    public Song() {}

    public Song(String songName, int yearWritten, int length,
                String genre, int writerID, int artistID,
                int albumID) {

        this.songName = songName;
        this.yearWritten = yearWritten;
        this.length = length;
        this.genre = genre;
        this.writerID = writerID;
        this.artistID = artistID;
        this.albumID = albumID;
    }

    public int getSongID() { return songID; }
    public String getSongName() { return songName; }
    public int getYearWritten() { return yearWritten; }
    public int getLength() { return length; }
    public String getGenre() { return genre; }
    public int getWriterID() { return writerID; }
    public int getArtistID() { return artistID; }
    public int getAlbumID() { return albumID; }

    public void setSongID(int songID) { this.songID = songID; }
    public void setSongName(String songName) { this.songName = songName; }
    public void setYearWritten(int yearWritten) { this.yearWritten = yearWritten; }
    public void setLength(int length) { this.length = length; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setWriterID(int writerID) { this.writerID = writerID; }
    public void setArtistID(int artistID) { this.artistID = artistID; }
    public void setAlbumID(int albumID) { this.albumID = albumID; }
}
