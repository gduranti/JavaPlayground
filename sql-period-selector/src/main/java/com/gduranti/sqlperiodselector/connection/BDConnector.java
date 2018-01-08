package com.gduranti.sqlperiodselector.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDConnector {

    private static Connection connection;

    public static Connection getInstance(ConnectionProperties connectionProperties) {
        if (connection == null) {
            try {
                connection = getConnection(connectionProperties);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }

    private static Connection getConnection(ConnectionProperties connectionProperties) throws Exception {
        System.out.println("-------- Oracle JDBC Connection Testing ------");
        Class.forName("oracle.jdbc.driver.OracleDriver");

        System.out.println("Oracle JDBC Driver Registered!");
        Connection connection = DriverManager.getConnection(connectionProperties.getUrl(), connectionProperties.getUser(), connectionProperties.getPassword());

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }

        return connection;
    }

}
