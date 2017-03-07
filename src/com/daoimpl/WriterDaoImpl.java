package com.daoimpl;

import com.dao.WriterDao;
import com.entities.Writer;
import com.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class WriterDaoImpl implements WriterDao {
    @Override
    public void createWriterTable() {
        Connection connection = null;
        Statement statement = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS writer (writerID int primary key unique auto_increment," +
                    "writerName varchar(55))");

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
    public void insert(Writer writer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO writer (writerName)" +
                    "VALUES (?)"); // "VALUES(?, ?)" // //protects us from SQL Injection Attacks
            preparedStatement.setString(1, writer.getWriterName());
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
    public Writer selectById(int writerID) {
        Writer writer = new Writer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("SELECT * FROM writer WHERE writerID = ?");
            preparedStatement.setInt(1, writerID);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                writer.setWriterID(resultSet.getInt("writerID"));
                writer.setWriterName(resultSet.getString("writerName"));
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

        return writer;
    }

    @Override
    public List<Writer> selectAll() {
        List<Writer> writers = new ArrayList<Writer>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
//            connection = Database.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM writer");

            while (resultSet.next()) {
                Writer writer = new Writer();
                writer.setWriterID(resultSet.getInt("writerID"));
                writer.setWriterName(resultSet.getString("writerName"));

                writers.add(writer);
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

        return writers;
    }

    @Override
    public void delete(int writerID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM writer WHERE writerID = ?");
            preparedStatement.setInt(1, writerID);
            preparedStatement.executeUpdate();

            System.out.println("DELETE FROM writer WHERE writerID = ?");

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
    public void update(Writer writer, int writerID) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
//            connection = Database.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE writer SET " +
                    "writerName = ? WHERE writerID = ?");

            preparedStatement.setString(1, writer.getWriterName());
            preparedStatement.setInt(2, writerID);
            preparedStatement.executeUpdate();

            System.out.println("UPDATE writer SET " +
                    "writerName = ? WHERE writerID = ?");

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
