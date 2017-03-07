package com.daoimpl;

import com.dao.ArtistDao;
import com.entities.Artist;
import com.util.Database;
import com.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ArtistDaoImpl implements ArtistDao {
    @Override
    public void createArtistTable() {
        Connection connection = null;
        Statement statement = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS artist (artistID int primary key unique auto_increment," +
                    "artistName varchar(55))");

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
    public void insert(Artist artist) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO artist (artistName)" +
                    "VALUES (?)"); // "VALUES(?, ?)" // //protects us from SQL Injection Attacks
            preparedStatement.setString(1, artist.getArtistName());
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
    public Artist selectById(int artistID) {
        Artist artist = new Artist();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM artist WHERE artistID = ?");
            preparedStatement.setInt(1, artistID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                artist.setArtistID(resultSet.getInt("artistID"));
                artist.setArtistName(resultSet.getString("artistName"));
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

        return artist;
    }

    @Override
    public List<Artist> selectAll() {
        List<Artist> artists = new ArrayList<Artist>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM artist");

            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setArtistID(resultSet.getInt("artistID"));
                artist.setArtistName(resultSet.getString("artistName"));

                artists.add(artist);
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

        return artists;
    }

    @Override
    public void delete(int artistID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM artist WHERE artistID = ?");
            preparedStatement.setInt(1, artistID);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM artist WHERE artistID = ?");

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
    public void update(Artist artist, int artistID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE artist SET " +
                    "artistName = ? WHERE artistID = ?");

            preparedStatement.setString(1, artist.getArtistName());
            preparedStatement.setInt(2, artistID);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE artist SET " +
                    "artistName = ? WHERE artistID = ?");

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
