package pl.zajacp.controller.admin;

import pl.zajacp.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddExercise")
public class ServletAdminExercisesAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Exercise ex = new Exercise(title, description);

        if (ex.save()) {
            request.setAttribute("add", "complete");
        } else {
            request.setAttribute("add", "failed");
        }

        getServletContext().getRequestDispatcher("/jsp/manageExercisesAdd.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/jsp/manageExercisesAdd.jsp").forward(request, response);
    }
}
