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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        Integer id = null;

        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if ("delete".equals(action) && id != null) {
            UserGroup ug = UserGroup.loadById(id);
            if (ug != null) {
                if (ug.delete()) {
                    request.setAttribute("delete", "complete");
                } else {
                    request.setAttribute("delete", "failed");
                }
            }
        } else if ("modify".equals(action) && id != null) {
            response.sendRedirect("");
            return;
        }

        List<UserGroup> userGroups = Arrays.asList(UserGroup.loadAll());
        request.setAttribute("userGroups", userGroups);
        getServletContext().getRequestDispatcher("/jsp/manageUserGroups.jsp").forward(request, response);
    }
}