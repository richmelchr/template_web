package com.dao;

import com.entities.Artist;

import java.util.List;

public interface ArtistDao {
    void createArtistTable();
    void insert(Artist artist);

    Artist selectById(int artistID);
    List<Artist> selectAll();

    void delete(int artistID);
    void update(Artist artist, int artistID);
}
