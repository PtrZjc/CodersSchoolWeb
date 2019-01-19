package pl.zajacp.controller;

import pl.zajacp.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        Solution[] solutions = Solution.loadAll(numberSolutions);
        request.setAttribute("solutions", solutions);

        getServletContext().getRequestDispatcher("/jsp/index.jsp").forward(request, response);
    }
}

