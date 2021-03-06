package pl.zajacp.controller.admin;

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
        String name = request.getParameter("groupName");
        Integer id = Integer.parseInt(request.getParameter("id"));

        UserGroup ug = UserGroup.loadById(id);
        ug.setName(name);

        if (ug.save()) {
            request.setAttribute("update", "complete");
        } else {
            request.setAttribute("update", "failed");
        }

        List<UserGroup> userGroups = Arrays.asList(UserGroup.loadAll());
        request.setAttribute("userGroups", userGroups);
        getServletContext().getRequestDispatcher("/jsp/manageUserGroups.jsp").forward(request, response);

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
            UserGroup ug = UserGroup.loadById(id);

            if ("delete".equals(action) && ug != null) {
                if (ug.delete()) {
                    request.setAttribute("delete", "complete");
                } else {
                    request.setAttribute("delete", "failed");
                }

            } else if ("modify".equals(action) && ug != null) {
                request.setAttribute("modified", ug);
                getServletContext().getRequestDispatcher("/jsp/manageUserGroupsModify.jsp").forward(request, response);
                return;
            }
        }

        List<UserGroup> userGroups = Arrays.asList(UserGroup.loadAll());
        request.setAttribute("userGroups", userGroups);
        getServletContext().getRequestDispatcher("/jsp/manageUserGroups.jsp").forward(request, response);
    }
}