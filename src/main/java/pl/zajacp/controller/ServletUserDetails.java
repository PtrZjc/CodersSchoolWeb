package pl.zajacp.controller;

import pl.zajacp.model.Exercise;
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

@WebServlet("/UserDetails")
public class ServletUserDetails extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();

        int id = Integer.parseInt(request.getParameter("id"));

        User user = User.loadById(id);
        List<Exercise> exercises = Arrays.asList(Exercise.loadByUserId(id));

        request.setAttribute("user", user);
        request.setAttribute("exercises", exercises);

        getServletContext().getRequestDispatcher("/jsp/userDetails.jsp").forward(request, response);
    }
}