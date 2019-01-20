package pl.zajacp.controller;

import pl.zajacp.model.Solution;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/")
public class ServletHome extends HttpServlet {

    public static int numberSolutions;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();

        numberSolutions = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
        List<Solution> solutions = Arrays.asList(Solution.loadAll(numberSolutions));

        request.setAttribute("solutions", solutions);

        getServletContext().getRequestDispatcher("/jsp/indexLastSolutions.jsp").forward(request, response);
    }
}

