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

import static pl.zajacp.controller.HomeServlet.numberSolutions;

@WebServlet("/SolutionDetails")
public class SolutionDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        int solutionId = Integer.parseInt(request.getParameter("id"));

        List<Solution> solutions = (List) sess.getAttribute("solutions");
        if (solutions == null) {
            solutions = Arrays.asList(Solution.loadAll(numberSolutions));
        }

        for (int i = 0; i < solutions.size(); i++) {
            if (solutions.get(i).getId() == solutionId) {
                if (solutions.get(i).getUpdated() == null) {
                    solutions.get(i).setUpdated(solutions.get(i).getCreated());
                }
                sess.setAttribute("userDetailedSolution", solutions.get(i));
            }
        }
        getServletContext().getRequestDispatcher("/jsp/detailedSolution.jsp").forward(request, response);
    }
}
