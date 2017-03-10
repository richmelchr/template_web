
package com.daoimpl;

import com.dao.AlbumDao;
import com.entities.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl implements AlbumDao {

    @Override
    public void createAlbumTable() {
        Connection connection = null;
        Statement statement = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS album " +
                    "(albumID int primary key unique auto_increment," +
                    "albumName varchar(55), yearRel int)");

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
    public void insert(Album album) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO album (albumName,yearRel)" +
                    "VALUES (?, ?)"); // "VALUES(?, ?)" // //protects us from SQL Injection Attacks
            preparedStatement.setString(1, album.getAlbumName());
            preparedStatement.setInt(2, album.getYearRel());
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
    public Album selectById(int albumID) {
        Album album = new Album();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM album WHERE albumID = ?");
            preparedStatement.setInt(1, albumID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                album.setAlbumID(resultSet.getInt("albumID"));
                album.setAlbumName(resultSet.getString("albumName"));
                album.setYearRel(resultSet.getInt("yearRel"));
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

        return album;
    }

    @Override
    public List<Album> selectAll() {
        List<Album> albums = new ArrayList<Album>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM album");

            while (resultSet.next()) {
                Album album = new Album();
                album.setAlbumID(resultSet.getInt("albumID"));
                album.setAlbumName(resultSet.getString("albumName"));
                album.setYearRel(resultSet.getInt("yearRel"));
                albums.add(album);
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

        return albums;
    }

    @Override
    public void delete(int albumID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM album WHERE albumID = ?");
            preparedStatement.setInt(1, albumID);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM album WHERE albumID = ?");

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
    public void update(Album album, int albumID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE album SET " +
                    "albumName = ?, yearRel = ? WHERE albumID = ?");

            preparedStatement.setString(1, album.getAlbumName());
            preparedStatement.setInt(2, album.getYearRel());
            preparedStatement.setInt(3, albumID);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE album SET " +
                    "albumName = ?, yearRel = ? WHERE albumID = ?");

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
