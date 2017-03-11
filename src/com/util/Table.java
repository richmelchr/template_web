package com.util;


import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


public class Table extends Database {

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
        String fieldName, fieldType, tableName = this.getClass().getSimpleName().toLowerCase();

        if (!doesTableExist(tableName)) {
            Connection connection = getConnection();
            PreparedStatement preStat = null;

            boolean worked, error = true;
            Field[] fields = this.getClass().getDeclaredFields();


            for (int i = 0; i < fields.length; i++) {
                fieldName = fields[i].getName();
                fieldType = fields[i].getGenericType().getTypeName();

                if (i == 0) { // Create Table with first Column
                    try {
                        preStat = connection.prepareStatement("CREATE TABLE IF NOT EXISTS " +
                                tableName + " (" + tableName + "ID" +
                                " INT PRIMARY KEY UNIQUE auto_increment)");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    pushToDB(preStat);

                } else { // Add all additional Column's
                    if (Objects.equals(fieldType, "int")) {
                        worked = addColumn(fieldName, true, false);
                    } else if (Objects.equals(fieldType, "java.lang.String")) {
                        worked = addColumn(fieldName, false, true);
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

    public boolean addColumn(String colName, boolean colInt, boolean colString) {
        Connection connection = getConnection();
        PreparedStatement preStat = null;
        String tableName = this.getClass().getSimpleName(); // return name of table

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
            preStat = connection.prepareStatement("DROP TABLE " + tableName);
            return pushToDB(preStat);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert() {
        Connection connection = getConnection();
        PreparedStatement preStat = null;
        String tableName = this.getClass().getSimpleName();
        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();

        columns.append(" (");
        values.append(" (");

        for (Field aField : fields) {
            columns.append(aField.getName()).append(",");
            values.append("?,");
        }

        columns.deleteCharAt(columns.length() - 1);
        values.deleteCharAt(values.length() - 1);
        columns.append(")"); // result (firstValue,secondValue,thirdValue)
        values.append(")"); // result: (?,?,?)

        try {
            preStat = connection.prepareStatement("INSERT INTO " +
                    tableName + columns + " VALUES" + values + ""); // build preStat statement
            for (int i = 0; i < fields.length; ++i) {
                Table Obj = this; // get instance of Object
                if (Objects.equals("int", fields[i].getGenericType().getTypeName())) { // if Object field is type int
                    preStat.setInt(i + 1, (Integer) fields[i].get(Obj)); //get Object field value
                } else if (Objects.equals("java.lang.String", fields[i].getGenericType().getTypeName())) { // if Object field is type String
                    preStat.setString(i + 1, (String) fields[i].get(Obj)); //get Object field value
                } else {
                    // operation only works with integers and Strings. if more types
                    // are required, add additional 'else if' statements to check
                    // for types in the Object
                    System.out.println("Column type of variable not found");
                    return false;
                }
            }
            pushToDB(preStat); // push preparedStatement to SQL Database Table

        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean delete(int playlistID) {
        Connection connection = getConnection();
        PreparedStatement preStat = null;
        String tableName = this.getClass().getSimpleName();

        try {
            preStat = connection.prepareStatement("DELETE FROM " +
                    tableName + " WHERE " + tableName + "ID" + " = ?");
            preStat.setInt(1, playlistID);
            return pushToDB(preStat);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



}
