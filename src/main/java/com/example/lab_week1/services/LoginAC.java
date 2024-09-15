package com.example.lab_week1.services;

import com.example.lab_week1.repositories.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAC {
    public int login (String us, String pw){
        int result = -1;
        try(Connection conn = ConnectDB.getConnection()){
            String query = "SELECT * FROM account WHERE account_id = ? AND password = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)){
                ps.setString(1, us);
                ps.setString(2, pw);
                try (ResultSet rs = ps.executeQuery()){
                    if(rs.next()){
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
