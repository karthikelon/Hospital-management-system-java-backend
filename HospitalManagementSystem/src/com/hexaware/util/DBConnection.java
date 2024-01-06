package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    // Private constructor to prevent instantiation
    private DBConnection() {
    }

    // Static method to get a connection
    public static Connection getConnection() {
        if (connection == null) {
            // If connection is not already established, create a new connection
            connection = createConnection();
        }
        return connection;
    }

    // Private method to create a new connection
    private static Connection createConnection() {
        String connectionString = PropertyUtil.getPropertyString();
        Connection con = null;

        try {
            con = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }
    public static void main(String []args) {
    	//System.out.println(getConnection());
    }
}

