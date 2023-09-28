package com.shopApp.customer;

import com.shopApp.dbHandler.DataFetcher;

public class Validator {
    public static Boolean isValid(String uname, String pass) {
        String dbPass = DataFetcher.fetchPassword(uname);
        return dbPass != null && dbPass.equals(pass);
    }
}
