package com.daoimpl;

import com.dao.SongDao;
import com.entities.Song;
import com.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SongDaoImpl implements SongDao {
    @Override
    public void createSongTable() {
        Connection connection = null;
        Statement statement = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS song (songID int primary key unique auto_increment," +
                    "songName varchar(11), yearWritten varchar(11), length int, genre varchar(11)," +
                    "writerID int key, artistID int key, albumID int key)");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void insert(Song song) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO song (songName,yearWritten,length,genre,writerID,artistID,albumID)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)"); // "VALUES(?, ?)" // //protects us from SQL Injection Attacks
            preparedStatement.setString(1, song.getSongName());
            preparedStatement.setInt(2, song.getYearWritten());
            preparedStatement.setInt(3, song.getLength());
            preparedStatement.setString(4, song.getGenre());
            preparedStatement.setInt(5, song.getWriterID());
            preparedStatement.setInt(6, song.getArtistID());
            preparedStatement.setInt(7, song.getAlbumID());

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public Song selectById(int songID) {
        Song song = new Song();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM song WHERE songID = ?");
            preparedStatement.setInt(1, songID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                song.setSongID(resultSet.getInt("songID"));
                song.setSongName(resultSet.getString("songName"));
                song.setYearWritten(resultSet.getInt("yearWritten"));
                song.setLength(resultSet.getInt("length"));
                song.setGenre(resultSet.getString("genre"));
                song.setWriterID(resultSet.getInt("writerID"));
                song.setArtistID(resultSet.getInt("artistID"));
                song.setAlbumID(resultSet.getInt("albumID"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return song;
    }

    @Override
    public List<Song> selectAll() {
        List<Song> songs = new ArrayList<Song>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM song");

            while (resultSet.next()) {
                Song song = new Song();
                song.setSongID(resultSet.getInt("songID"));
                song.setSongName(resultSet.getString("songName"));
                song.setYearWritten(resultSet.getInt("yearWritten"));
                song.setLength(resultSet.getInt("length"));
                song.setGenre(resultSet.getString("genre"));
                song.setWriterID(resultSet.getInt("writerID"));
                song.setArtistID(resultSet.getInt("artistID"));
                song.setAlbumID(resultSet.getInt("albumID"));
                songs.add(song);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return songs;
    }

    @Override
    public void delete(int songID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM song WHERE songID = ?");
            preparedStatement.setInt(1, songID);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM song WHERE songID = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Song song, int songID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE song SET " +
                    "songName = ?, yearWritten = ?, length = ?, genre = ?, writerID = ?," +
                    "artistID = ?, albumID = ? WHERE songID = ?");

            preparedStatement.setString(1, song.getSongName());
            preparedStatement.setInt(2, song.getYearWritten());
            preparedStatement.setInt(3, song.getLength());
            preparedStatement.setString(4, song.getGenre());
            preparedStatement.setInt(5, song.getWriterID());
            preparedStatement.setInt(6, song.getArtistID());
            preparedStatement.setInt(7, song.getAlbumID());
            preparedStatement.setInt(8, songID);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE song SET " +
                    "songName = ?, yearWritten = ?, length = ?, genre = ?, writerID = ?, " +
                    "artistID = ?, albumID = ? WHERE songID = ?");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
