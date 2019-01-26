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

import static pl.zajacp.controller.ServletHome.numberSolutions;

@WebServlet(name = "DeleteSolutionServlet")
public class DeleteSolutionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sess = request.getSession();
        int solutionId = Integer.parseInt(request.getParameter("id"));
        String solutionAction = request.getParameter("do");


        if ("del".equals(solutionAction)) {
            Solution solution = Solution.loadById(solutionId);
            solution.delete();
            List<Solution> solutions = Arrays.asList(Solution.loadAll(numberSolutions));
            sess.setAttribute("message", "The record was deleted from the database");

            response.sendRedirect(request.getContextPath() + "/ServletHome");;
        }

    }
}
