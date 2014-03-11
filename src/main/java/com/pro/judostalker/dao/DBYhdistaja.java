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
        connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-197-246-197.compute-1.amazonaws.com:5432/d6abu1e80r7fg9", "jymbgkoiyxaoqf", "2z7U85qMxivvKzu1-TCVhMYubJ"); 
        return connection;
    }
}
