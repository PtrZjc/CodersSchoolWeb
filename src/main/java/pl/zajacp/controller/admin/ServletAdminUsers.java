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

@WebServlet("/ManageUsers")
public class ServletAdminUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Integer userGroupID = Integer.parseInt(request.getParameter("userGroupID"));
        Integer id = Integer.parseInt(request.getParameter("id"));


        User us = User.loadById(id);

        us.setUsername(username);
        us.setEmail(email);
        us.setUserGroup(UserGroup.loadById(userGroupID));
        us.setPassword(password);

        if (us.save()) {
            request.setAttribute("update", "complete");
        } else {
            request.setAttribute("update", "failed");
        }

        List<User> users = Arrays.asList(User.loadAll());
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/jsp/manageUsers.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Integer id = null;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if ("delete".equals(action) || "modify".equals(action)) {
            User us = User.loadById(id);
            if ("delete".equals(action) && us != null) {
                if (us.delete()) {
                    request.setAttribute("delete", "complete");
                } else {
                    request.setAttribute("delete", "failed");
                }

            } else if ("modify".equals(action) && us != null) {
                request.setAttribute("modified", us);
                List<UserGroup> userGroups = Arrays.asList(UserGroup.loadAll());
                request.setAttribute("userGroups", userGroups);
                getServletContext().getRequestDispatcher("/jsp/manageUsersModify.jsp").forward(request, response);
                return;
            }
        }

        List<User> users = Arrays.asList(User.loadAll());
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/jsp/manageUsers.jsp").forward(request, response);
    }
}
