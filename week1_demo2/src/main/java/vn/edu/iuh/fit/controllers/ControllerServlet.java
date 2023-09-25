package vn.edu.iuh.fit.controllers;

import vn.edu.iuh.fit.entities.Account;
import vn.edu.iuh.fit.entities.GrantAccess;

import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.services.AccountServices;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/ControlServlet"})
public class ControllerServlet extends HttpServlet {
    @Inject
    AccountServices services;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action) {
            case "login":
                logIn(request, response);
                break;
            case "logout":
                logOut(request, response);
                break;
            default:
                break;
        }
    }

    public void logIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("txtUserName");
        String uPassword = request.getParameter("txtPassWord");
        PrintWriter out = response.getWriter();
        GrantAccess gr = services.getAccountRole(uName, uPassword);
        Account ac = services.accountLogin(uName, uPassword);
        RequestDispatcher dispatcher = request.getRequestDispatcher("userLogin.jsp");
        request.setAttribute("role", gr);
        request.setAttribute("userLogin", ac);
        dispatcher.forward(request, response);

    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uName = request.getParameter("userID");
        boolean logout = services.accountLogout(uName);
        if (logout) {
            request.setAttribute("userLogout", logout);
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("index.jsp");
        }
    }
}

