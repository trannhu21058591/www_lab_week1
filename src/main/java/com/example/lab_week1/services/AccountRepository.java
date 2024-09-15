package com.example.lab_week1.services;

import com.example.lab_week1.entities.Account;
import com.example.lab_week1.repositories.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
//    Connection conn = null;
//    PreparedStatement ps = null;
//    ResultSet rs = null;

    public ArrayList<Account> getAllAccount(){
        ArrayList<Account> list = new ArrayList<Account>();
        String query = "SELECT * FROM Account";
        try {
            Connection conn = ConnectDB.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String accountID = rs.getString(0);
                String fullName = rs.getString(1);
                String password = rs.getString(2);
                String email = rs.getString(3);
                String phone = rs.getString(4);
                Byte state = rs.getByte(5);

                Account ac = new Account(accountID,fullName,password,email,phone,state);
                list.add(ac);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }



    public String addAccount(String fullName, String password, String email, String phone, int status) {
        try (Connection conn = ConnectDB.getConnection()) {
            String sql = "INSERT INTO account (full_name, password, email, phone, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, fullName);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, status);
            ps.executeUpdate();

            return "Account added successfully.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error adding account.";
        }
    }
}
