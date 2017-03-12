package com.util;

import java.util.List;
import java.awt.*;
import java.sql.*;
import java.util.*;

class Database {
    private String dbName = "ser322";
    private final String user = "root";
    private final String passwrd = "gt46u7";

    Database() {
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

    boolean pushToDB(PreparedStatement preStat) {
        Connection connection = null;
        try {
            connection = getConnection();
            preStat.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (preStat != null) {
                try {
                    preStat.close();
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

    ResultSet getFromDB(PreparedStatement preStat) {
        Connection connection = null;
        try {
            connection = getConnection();
            return preStat.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    String getDbName() { return dbName; }
}
