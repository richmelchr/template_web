package com.util;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


public class Table extends Database {

//    public Table(String tableName) {
//        Table.tableName = tableName;
//        String temp = "CREATE TABLE IF NOT EXISTS " +
//                tableName + " (id int primary key unique auto_increment)";
//        Database.pushToDB(tableName);
//    }

    public boolean doesTableExist(String tableName) {
        Connection connection = getConnection();
        try { // does table already exist?
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createTable() {
        Connection connection = getConnection();
        PreparedStatement preStat = null;

        String fieldName, fieldType, tableName = this.getClass().getSimpleName().toLowerCase();
        boolean worked, error = true;
        Field[] fields = this.getClass().getDeclaredFields();

        if (!doesTableExist(tableName)) {

            for (int i = 0; i < fields.length; i++) {
                fieldName = fields[i].getName();
                fieldType = fields[i].getGenericType().getTypeName();

                if (i == 0) { // Create Table with first Column
                    try {
                        if (Objects.equals(fieldType, "int")) { // if type of field is Int
                            preStat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                                    tableName + " (" + fieldName + " INT(11))");
                        } else if (Objects.equals(fieldType, "java.lang.String")) {
                            preStat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                                    tableName + " (" + fieldName + " VARCHAR(55))");
                        } else {
                            System.out.println("Column type of variable not found");
                            return false;
                        }

                        pushToDB(preStat);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else { // Add all additional Column's
                    if (Objects.equals(fieldType, "int")) {
                        worked = addColumn(tableName, fieldName, true, false);
                    } else if (Objects.equals(fieldType, "java.lang.String")) {
                        worked = addColumn(tableName, fieldName, false, true);
                    } else {
                        worked = false;
                        System.out.println("Not all Column's added");
                    }

                    if (!worked) {
                        error = false;
                    }
                }
                if (!error) {
                    System.out.println("not all Column's were added");
                    return false; // all columns not added
                }
            }
            return true;
        }
        return false;
    }


    public boolean addColumn(String tableName, String colName, boolean colInt, boolean colString) {
        Connection connection = getConnection();
        PreparedStatement preStat = null;

        try {
            if (colInt) {
                preStat = connection.prepareStatement("ALTER TABLE " + tableName +
                        " ADD COLUMN " + colName + " INT(11)");
            } else if (colString) {
                preStat = connection.prepareStatement("ALTER TABLE " + tableName +
                        " ADD COLUMN " + colName + " VARCHAR(55)");
            } else {
                System.out.println("Column type of variable not found for .addColumn");
                return false;
            }

            return pushToDB(preStat);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean dropTable() {
        Connection connection = getConnection();
        PreparedStatement preStat = null;

        String tableName = this.getClass().getSimpleName().toLowerCase();

        try {
            preStat = connection.prepareStatement("DROP TABLE ?");
            preStat.setString(1, tableName);
            return pushToDB(preStat);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public static boolean insert(Object table) { // static means you can access this method without declaring new Table
        String tableName = table.getClass().getSimpleName();
        Field[] fields = table.getClass().getDeclaredFields();

        for (Field aField : fields) {
            System.out.println("--------------------------");
            System.out.println(aField.getName());
            System.out.println(aField.getGenericType());
            System.out.println("---------------------------");
        }


        return true;

    }


}
