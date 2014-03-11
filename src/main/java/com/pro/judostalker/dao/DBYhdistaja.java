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
        connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-204-32-91.compute-1.amazonaws.com:5432/d8qcgq67k5a5u1", "onmzifautaqinx", "SGrD5AD-XARG6cDPW4XQef89eT"); 
        return connection;
    }
}
