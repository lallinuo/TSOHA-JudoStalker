/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pro.judostalker.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lalli
 */
public class DBYhdistaja {

    public DBYhdistaja() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

    }

    public Connection yhdista() throws SQLException {
        Connection connection = null;
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "tsoha2014"); 
        return connection;
    }
}
