package com.dao;

import com.entities.Writer;

import java.util.List;

public interface WriterDao {

    void createWriterTable();
    void insert(Writer writer);

    Writer selectById(int writerID);
    List<Writer> selectAll();

    void delete(int writerID);
    void update(Writer writer, int writerID);
}
