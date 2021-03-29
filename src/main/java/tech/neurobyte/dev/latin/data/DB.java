/*
 * Copyright © 2021 NeuroByte Tech. All rights reserved.
 *
 * NeuroByte Tech is the Developer Company of Rohan Mathew.
 *
 * Project: latinvocab
 * File Name: DB.java
 * Last Modified: 29/03/2021, 20:40
 */

package tech.neurobyte.dev.latin.data;

import tech.tablesaw.api.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    private static Connection c;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:26257/latin", "root", "");
        } catch (ClassNotFoundException e) {
            System.out.println("Unable to find PostgreSQL connector class");
            System.exit(1);
        } catch (SQLException e) {
            System.out.println("Cannot connect to db");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static Table query(String sql) {
        try {
            Statement st = c.createStatement();
            return Table.read().db(st.executeQuery(sql));

        } catch (SQLException e) {
            return null;
        }
    }
}
