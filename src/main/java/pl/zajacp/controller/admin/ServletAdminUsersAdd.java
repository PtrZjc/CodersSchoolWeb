package pl.zajacp.controller.admin;

import pl.zajacp.model.User;
import pl.zajacp.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/AddUser")
public class ServletAdminUsersAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer userGroupID = Integer.parseInt(request.getParameter("userGroupID"));

        User us = new User(username,email,password,UserGroup.loadById(userGroupID));

        if (us.save()) {
            request.setAttribute("add", "complete");
        } else {
            request.setAttribute("add", "failed");
        }

        getServletContext().getRequestDispatcher("/jsp/manageUsersAdd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<UserGroup> userGroups = Arrays.asList(UserGroup.loadAll());
        request.setAttribute("userGroups", userGroups);

        getServletContext().getRequestDispatcher("/jsp/manageUsersAdd.jsp").forward(request, response);
    }
}