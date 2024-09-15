package com.example.lab_week1.controllers;

import com.example.lab_week1.repositories.ConnectDB;
import com.example.lab_week1.services.AccountRepository;
import com.example.lab_week1.services.LoginAC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Control-Servlet", value = "/ControlServlet")
public class ControlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String action = req.getParameter("action");

        switch (action) {
            case "addAccount":
                String fullName = req.getParameter("full_name");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                String phone = req.getParameter("phone");
                int status = Integer.parseInt(req.getParameter("status"));

                // Gọi phương thức addAccount trong AccountRepository
                AccountRepository accountRepo = new AccountRepository();
                String result = accountRepo.addAccount(fullName, password, email, phone, status);
                resp.getWriter().println(result);
                break;
//            case "updateAccount":
//                AccountRepository.updateAccount(request, response);  // Gọi phương thức xử lý trong lớp xử lý
//                break;
//            case "deleteAccount":
//                AccountRepository.deleteAccount(request, response);  // Gọi phương thức xử lý trong lớp xử lý
//                break;
            default:
                resp.getWriter().println("Invalid action");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String us = req.getParameter("us");
        String pw = req.getParameter("pw");
        LoginAC loginBean = new LoginAC();
        int result = loginBean.login(us, pw);
        PrintWriter out = resp.getWriter();
        if(result == 1){
            out.println("<h1>WELLCOME "+ us + " TO WEBSITE</h1>");
        } else if (result == 0){
            out.println("<h1>WRONG PASSWORD</h1>");
        } else {
            out.println("<h1>WRONG USERNAME</h1>");
        }
    }
}
