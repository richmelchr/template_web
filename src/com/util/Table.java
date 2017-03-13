package com.util;

import com.entities.Playlist;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.sql.*;
import java.util.*;

public class Table extends Database {

    public boolean doesTableExist() {
        return doesTableExist(this.getClass().getSimpleName().toLowerCase());
    }

    public boolean doesTableExist(String tableName) {
        Connection connection = getConnection();
        ResultSet rs = null;
        try { // does table already exist?
            DatabaseMetaData md = connection.getMetaData();
            rs = md.getTables(null, null, tableName, null);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean createTable() {
        String fieldName, fieldType;
        String tableName = this.getClass().getSimpleName().toLowerCase();

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
                }
            }
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("ERROR: Table already exists");
            return false;
        }
        return true;
    }

    public boolean addColumn(String colName, boolean colInt, boolean colString) {
        String tableName = this.getClass().getSimpleName().toLowerCase(); // return name of table
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }

        ArrayList<String> columns = getColumns();
        for (String column : columns) {
            if (Objects.equals(column, colName)) {
                System.out.println("ERROR: \"" + colName + "\" already present in " + tableName);
                return false;
            }
        }

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
            pushToDB(preStat);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean removeColumn(String colName) {
        String tableName = this.getClass().getSimpleName().toLowerCase(); // return name of table
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }

        ArrayList<String> columns = getColumns();
        boolean columnExists = false;
        for (String column : columns) {
            if (Objects.equals(column, colName)) {
                columnExists = true;
            }
        }
        if (!columnExists) {
            System.out.println("ERROR: \"" + colName + "\" not present in " + tableName);
            return false;
        }

        Connection connection = getConnection();
        PreparedStatement preStat = null;

        try {
            preStat = connection.prepareStatement("ALTER TABLE " +
                    tableName + " DROP " + colName);
            pushToDB(preStat);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return true;
    }

    public ArrayList<String> getColumns() {
        String tableName = this.getClass().getSimpleName().toLowerCase(); // return name of table
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return null;
        }

        ArrayList<String> columns = new ArrayList<String>();
        Connection connection = getConnection();
        PreparedStatement preStat = null;
        ResultSet rs = null;

        try {
            preStat = connection.prepareStatement("SELECT * FROM " + tableName);
            rs = getFromDB(preStat);
            ResultSetMetaData rsMetaDataM = rs.getMetaData();

            for (int i = 1; i < rsMetaDataM.getColumnCount() + 1; i++) {
                columns.add(rsMetaDataM.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preStat.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return columns;
    }

    public boolean dropTable(String password) {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!Objects.equals(password, "gt46u7")) {
            System.out.println("ERROR: Incorrect Password");
            return false;
        }
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }
        Connection connection = getConnection();
        PreparedStatement preStat = null;

        try {
            preStat = connection.prepareStatement("DROP TABLE " + tableName);
            pushToDB(preStat);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean insert() {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }
        if (!fieldColumnMatch()) {
            // consider adding solution to auto add a column if this error is called
            System.out.println("ERROR: Table does not contain a column for one of your fields, INSERT CANCELED");
            return false;
        }

        Connection connection = getConnection();
        PreparedStatement preStat = null;

        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        String pkey = getPrimaryKey();

        columns.append(" (");
        values.append(" (");

        for (Field aField : fields) {
            if (Modifier.isPrivate(aField.getModifiers())) {
                System.out.println("ERROR: " + tableName + " variable \"" + aField.getName() + "\" is Private. This will throw an Exception");
                return false;
            }

            if (!Objects.equals(aField.getName(), pkey)) {
                columns.append(aField.getName()).append(",");
                values.append("?,");
            }
        }

        columns.deleteCharAt(columns.length() - 1); // remove last comma
        values.deleteCharAt(values.length() - 1); // remove last comma
        columns.append(")"); // result (firstValue,secondValue,thirdValue)
        values.append(")"); // result: (?,?,?)

        try {
            preStat = connection.prepareStatement("INSERT INTO " +
                    tableName + columns + " VALUES" + values + ""); // build preStat statement

            preStat = setPreStat(preStat);
            pushToDB(preStat); // push preparedStatement to SQL Database Table

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean delete(int id) {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }

        if (!selectByID(id)) {
            // error already displayed by selectByID()
            return false;
        }

        String pkey = getPrimaryKey();
        Connection connection = getConnection();
        PreparedStatement preStat = null;

        try {
            preStat = connection.prepareStatement("DELETE FROM " +
                    tableName + " WHERE " + pkey + " = ?");
            preStat.setInt(1, id);
            pushToDB(preStat);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public boolean update(int id) {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }

        Connection connection = getConnection();
        PreparedStatement preStat = null;

        Field[] fields = this.getClass().getDeclaredFields();
        StringBuilder columns = new StringBuilder();
        String pkey = getPrimaryKey();

        for (Field aField : fields) {
            if (!Objects.equals(aField.getName(), pkey)) { // if aField is != to primary key
                columns.append(aField.getName()).append(" = ?, "); // build preStat insert
            }
        }
        columns.deleteCharAt(columns.length() - 2);

        try {
            preStat = connection.prepareStatement("UPDATE " + tableName +
                    " SET " + columns + "WHERE " + pkey + " = ?");

            preStat = setPreStat(preStat);
            preStat.setInt(fields.length, id);
            pushToDB(preStat);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preStat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (!selectByID(id)) {
            // error text handled inside selectByID()
            return false;
        }
        return true;
    }

    public boolean selectByID(int id) {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }
        String pkey = getPrimaryKey();
        Connection connection = getConnection();
        PreparedStatement preStat = null;
        ResultSet resultSet = null;
        boolean resultSetEmpty = true;

        Field[] fields = this.getClass().getDeclaredFields();
        ArrayList<String> columns = getColumns();

        try {
            preStat = connection.prepareStatement("SELECT * FROM " + tableName + " WHERE " + pkey + " = ?");
            preStat.setInt(1, id);
            resultSet = getFromDB(preStat);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 0: flag resultSet as containing data
        // 1: for each column, find a matching field. if they match, check for matching types
        // 2: then assign the value of the column to the field.
        // 3: then remove the SQL Column and Object Variable from a list of total columns and variables
        try {
            while (resultSet.next()) {
                resultSetEmpty = false; // 0
                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < columns.size(); j++) {
                        if (Objects.equals(fields[i].getName(), columns.get(j))) { // 1
                            try {
                                String fieldType = fields[i].getGenericType().getTypeName();
                                if (Objects.equals(fieldType, "int")) {
                                    fields[i].set(this, resultSet.getInt(columns.get(j))); // 2

                                } else if (Objects.equals(fieldType, "java.lang.String")) {
                                    fields[i].set(this, resultSet.getString(columns.get(j))); // 2

                                } else {
                                    System.out.println("-WARNING- Column type of variable not found");
                                }
                                columns.remove(columns.get(j)); // 3
                                fields[i].setAccessible(true); // 3

                            } catch (SQLException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                assert preStat != null;
                preStat.close();
                assert resultSet != null;
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (resultSetEmpty) {
            System.out.println("ERROR: " + tableName + " row #" + id + " does not exist");
            return false;
        }

        for (String column : columns) {
            System.out.println("-WARNING- This SQL Column did not have a matching Object variable | SQL Column: " + column);
        }
        for (Field aField : fields) {
            if (!aField.isAccessible()) {
                System.out.println("-WARNING- This Object variable did not have a matching SQL Column | Object Var: " + aField.getName());
            }
        }
        return true;
    }

    private PreparedStatement setPreStat(PreparedStatement preStat) {
        Field[] fields = this.getClass().getDeclaredFields();
        String pkey = getPrimaryKey();
        for (int i = 0; i < fields.length; ++i) {
            Table Obj = this; // get instance of Object

            if (!Objects.equals(fields[i].getName(), pkey)) { // if not primary key

                try {
                    if (Objects.equals("int", fields[i].getGenericType().getTypeName())) { // if Object field is type int
                        preStat.setInt(i, (Integer) fields[i].get(Obj)); //get Object field value
                    } else if (Objects.equals("java.lang.String", fields[i].getGenericType().getTypeName())) { // if Object field is type String
                        preStat.setString(i, (String) fields[i].get(Obj)); //get Object field value
                    } else {
                        // operation only works with integers and Strings. if more types
                        // are required, add additional 'else if' statements to check
                        // for types in the Object
                        System.out.println("Column type of variable not found");
                        return null;
                    }
                } catch (SQLException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return preStat;
    }

    public boolean fieldColumnMatch() {
        String tableName = this.getClass().getSimpleName().toLowerCase(); // return name of table
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }
        Field[] fields = this.getClass().getDeclaredFields();
        ArrayList<String> fieldNames = new ArrayList<String>();
        ArrayList<String> columns = getColumns();
        ArrayList<String> matches = new ArrayList<String>();

        for (Field field : fields) { // convert Field to ArrayList
            fieldNames.add(field.getName());
        }

        // add matching items to 'matches' ArrayList
        for (String name:fieldNames) {
            for (String column:columns) {
                if (Objects.equals(name, column)) {
                    matches.add(name);
                }
            }
        }
        for (String match:matches) {
            fieldNames.remove(match);
            columns.remove(match);
        }

        return (fieldNames.size() == 0);
    }

    public String getPrimaryKey() {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return null;
        }
        String pkey = null;
        Connection connection = getConnection();

        try {
            DatabaseMetaData md = connection.getMetaData();
            ResultSet rs = md.getPrimaryKeys(null, null, tableName);
            if (rs.next()) {
                pkey = rs.getString("COLUMN_NAME"); // get primary key name
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pkey == null) {
            System.out.println("ERROR: \"" + tableName + "\" does not have a primary key assigned");
        }
        return pkey;
    }

    /*
    public boolean selectAll(List newList) {
        String tableName = this.getClass().getSimpleName().toLowerCase();
        if (!doesTableExist(tableName)) {
            System.out.println("ERROR: Table does not exist");
            return false;
        }
        Field[] fields = this.getClass().getDeclaredFields();
        ArrayList<String> columns = getColumns();

        Connection connection = getConnection();
        PreparedStatement preStat = null;
        ResultSet resultSet = null;

        try {
            preStat = connection.prepareStatement("SELECT * FROM " + tableName);
            resultSet = getFromDB(preStat);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            while (resultSet.next()) {


                for (int i = 0; i < fields.length; i++) {
                    for (int j = 0; j < columns.size(); j++) {
                        if (Objects.equals(fields[i].getName(), columns.get(j))) { // 1
                            try {
                                String fieldType = fields[i].getGenericType().getTypeName();
                                if (Objects.equals(fieldType, "int")) {
                                    fields[i].set(this, resultSet.getInt(columns.get(j))); // 2

                                } else if (Objects.equals(fieldType, "java.lang.String")) {
                                    fields[i].set(this, resultSet.getString(columns.get(j))); // 2

                                }



                            } catch (SQLException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                preStat.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        return true;
    }
    */

}
