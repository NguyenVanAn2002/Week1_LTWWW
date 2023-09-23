package vn.edu.iuh.fit.controllers;


import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.edu.iuh.fit.entities.GrantAccess;
import vn.edu.iuh.fit.services.AccountServices;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/ControllerServlet","/control"})
public class ControllerServlet extends HttpServlet {
    @Inject
    private AccountServices services;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
        String uName = request.getParameter("TxtUserName");
        String uPassword  = request.getParameter("TxtPassWord");
        PrintWriter out = response.getWriter();
        GrantAccess ac = services.getAccountRole(uName, uPassword);
//        List<GrantAccess> dsAccount = services.getDsAccountRole();
        if (ac == null) {
            out.println("Login False");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin-home.jsp");
            request.setAttribute("user", services.getAccountRole(uName, uPassword));
//            request.setAttribute("dsuser", services.getDsAccountRole());
            dispatcher.forward(request, response);
//            String deleteBtn = request.getParameter("delete");
//            String updateBtn = request.getParameter("update");

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}