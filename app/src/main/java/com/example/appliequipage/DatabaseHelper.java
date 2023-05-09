package com.example.appliequipage;

import java.sql.*;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:mysql://10.3.141.95:3306/capteur";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void insertCounter(int counter) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.prepareStatement("INSERT INTO counter (value) VALUES (?)");
            stmt.setInt(1, counter);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void updateCounter(int counter) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.prepareStatement("UPDATE donnee SET valeur = ? WHERE id = 1");
            stmt.setInt(1, counter);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static int getCounter() throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT valeur FROM donnee");
            if (rs.next()) {
                return rs.getInt("value");
            } else {
                return 0;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
