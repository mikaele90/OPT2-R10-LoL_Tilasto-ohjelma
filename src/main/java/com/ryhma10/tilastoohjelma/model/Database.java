package com.ryhma10.tilastoohjelma.model;

import java.sql.*;
import com.ryhma10.tilastoohjelma.MainApp;

public class Database {

    public Database() {
        try {
            System.out.println("Attempting database connection...");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/LOLDATABASE?user=ryhma10&password=123456");
            if(!connection.isClosed()) {
                System.out.println("Connection established");
            }
        }
        catch(SQLException e) {
            System.out.println("Database error. Printing stacktrace...");
            e.printStackTrace();
        }
    }

}
