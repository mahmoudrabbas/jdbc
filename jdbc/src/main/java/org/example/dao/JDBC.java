package org.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "mra5553";
    private static final String DB_NAME = "company";
    private static Connection connection;


    public static Connection getConnection(){
        try {

            connection = DriverManager.getConnection(String.format("jdbc:mysql://%s:%d/%s",HOST,PORT,DB_NAME), USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return connection;
    }
}
