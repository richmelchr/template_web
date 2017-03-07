package com.dao;

import com.entities.Album;

import java.util.List;

public interface AlbumDao {
    void createAlbumTable();
    void insert(Album album);

    Album selectById(int albumID);
    List<Album> selectAll();

    void delete(int albumID);
    void update(Album album, int albumID);
}
