package com.dao;

import com.entities.Song;

import java.util.List;

public interface SongDao {

    void createSongTable();
    void insert(Song song);

    Song selectById(int songID);
    List<Song> selectAll();

    void delete(int songID);
    void update(Song song, int songID);
}
