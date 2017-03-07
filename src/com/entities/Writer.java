package com.entities;

public class Writer {
    private int writerID;
    private String writerName;

    public Writer() { }

    public Writer(String writerName) {
        this.writerName = writerName;
    }

    public int getWriterID() {
        return writerID;
    }
    public String getWriterName() {
        return writerName;
    }
    public void setWriterName(String writerName) {
        this.writerName = writerName;
    }
    public void setWriterID(int writerID) {
        this.writerID = writerID;
    }
}

