package com.example.lab_week1;

import com.example.lab_week1.services.LoginAC;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "test-servlet", value = "/doLogin")
public class test extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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
