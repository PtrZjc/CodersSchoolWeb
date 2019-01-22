package pl.zajacp.controller;

import pl.zajacp.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/ManageUserGroups")
public class ServletAdminUserGroups extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserGroup> userGroups = Arrays.asList(UserGroup.loadAll());
        request.setAttribute("userGroups", userGroups);
        getServletContext().getRequestDispatcher("/jsp/manageUserGroups.jsp").forward(request, response);

    }
}
