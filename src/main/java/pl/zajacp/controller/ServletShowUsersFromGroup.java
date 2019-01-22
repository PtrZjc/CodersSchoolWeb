package pl.zajacp.controller;

import pl.zajacp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/ShowUsersFromGroup")
public class ServletShowUsersFromGroup extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));

        List<User>users = Arrays.asList(User.loadAllByGroupId(id));
        request.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/jsp/showUsers.jsp").forward(request, response);
    }
}
