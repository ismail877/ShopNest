package com.shopApp.dbHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.shopApp.product.Product;

public class DataFetcher {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/shop?useUnicode=true&characterEncoding=UTF-8&useOldUTF8Behavior=true";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String fetchPassword(String uname) {
        String sql = "SELECT pass FROM customers WHERE uname=?";
        String dbPass = null;

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, uname);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dbPass = rs.getString("pass");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbPass;
    }

    public static List<String> fetchUsersInfo() {
        String sql = "SELECT uname, mail, gender, address FROM customers";
        List<String> ulist = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String temp = rs.getString(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getString(4);
                ulist.add(temp);
            }

        } catch (Exception e) {
            System.out.println("Problem in fetching user information");
            e.printStackTrace();
        }
        return ulist;
    }

    public static List<String> fetchProductsInfo() {
        String sql = "SELECT pid, pname, pdesc, pprice, pimg FROM products";
        List<String> ilist = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String temp = rs.getInt(1) + ":" + rs.getString(2) + ":" + rs.getString(3) + ":" + rs.getInt(4) + ":" + rs.getString(5);
                ilist.add(temp);
            }

        } catch (Exception e) {
            System.out.println("Problem in fetching product information");
            e.printStackTrace();
        }
        return ilist;
    }

    public static Product getProductById(int pid) {
        String sql = "SELECT pname, pprice FROM products WHERE pid=?";
        Product p = null;

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pname = rs.getString(1);
                    int pprice = rs.getInt(2);
                    p = new Product(pid, pname, pprice);
                }
            }

        } catch (Exception e) {
            System.out.println("Problem in fetching product by id");
            e.printStackTrace();
        }
        return p;
    }
}
