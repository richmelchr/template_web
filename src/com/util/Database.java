package com.util;

import java.util.List;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class Database {
    private String dbName;
    private final String user = "root";
    private final String passwrd = "gt46u7";
    private static ArrayList<String> tables;

    public Database(String dbName) {
        this.dbName = dbName;
        try {
            getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("unable to establish connection to: " + dbName);
        }
    }

    Connection getConnection() {
        Connection connection = null;
        String url = "jdbc:mysql://localhost:3306/" + dbName + "?useSSL=false";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, passwrd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    boolean pushToDB(PreparedStatement statement) {
        Connection connection = null;
        try {
            connection = getConnection();
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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



    public ArrayList<String> getTables() {return tables;}
    public String getDbName() { return dbName; }
}
