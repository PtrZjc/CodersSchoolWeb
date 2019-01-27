package pl.zajacp.controller.admin;

import pl.zajacp.model.UserGroup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddUserGroup")
public class ServletAdminUserGroupsAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String groupName = request.getParameter("groupName");

        UserGroup ug = new UserGroup(groupName);

        if (ug.save()) {
            request.setAttribute("add", "complete");
        } else {
            request.setAttribute("add", "failed");
        }

        getServletContext().getRequestDispatcher("/jsp/manageUserGroupsAdd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/jsp/manageUserGroupsAdd.jsp").forward(request, response);
    }
}
