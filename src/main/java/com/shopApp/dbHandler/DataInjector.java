package com.shopApp.dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInjector {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String addCustomer(String uname, String mail, String pass, String gender, String address) {
        String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=UTF-8&useOldUTF8Behavior=true";
        String username = "root";
        String password = "password";
        String sql = "INSERT INTO customers (uname, mail, pass, gender, address) VALUES (?, ?, ?, ?, ?)";
        String regStatus = "";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, uname);
            pstmt.setString(2, mail);
            pstmt.setString(3, pass);
            pstmt.setString(4, gender);
            pstmt.setString(5, address);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                regStatus = "Successfully Registered";
            } else {
                regStatus = "Fail to Register";
            }

        } catch (SQLException e) {
            System.out.println("Problem Occurred in Adding Customer");
            e.printStackTrace();
            regStatus = "Fail to Register";
        }
        return regStatus;
    }

    public static boolean addProduct(int pid, String pname, String pdesc, int pprice, String pimg) {
        String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=UTF-8&useOldUTF8Behavior=true";
        String user = "root";
        String password = "password";
        String sql = "INSERT INTO products (pid, pname, pdesc, pprice, pimg) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pid);
            ps.setString(2, pname);
            ps.setString(3, pdesc);
            ps.setInt(4, pprice);
            ps.setString(5, pimg);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Problem in adding product");
            e.printStackTrace();
            return false;
        }
    }
    public static boolean placeOrder(String username, String productIds, String orderDate) {
        String url = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=UTF-8&useOldUTF8Behavior=true";
        String user = "root";
        String password = "password";
        String sql = "INSERT INTO orders (username, product_ids, order_date) VALUES (?, ?, ?)";

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, productIds);
            ps.setString(3, orderDate);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Problem in placing order");
            e.printStackTrace();
            return false;
        }
    }
}
