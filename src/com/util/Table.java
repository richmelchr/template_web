package com.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Table extends Database {

    private static String tableName;
    private static ArrayList<String> fields;

    public Table() {}

//    public Table(String tableName) {
//        Table.tableName = tableName;
//        String temp = "CREATE TABLE IF NOT EXISTS " +
//                tableName + " (id int primary key unique auto_increment)";
//        Database.pushToDB(tableName);
//    }

    public String getTableName() {return tableName;}

    public boolean dropTable(Object table) {
        Connection connection = getConnection();
        PreparedStatement preStat = null;
        String tableName = table.getClass().getSimpleName();

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
