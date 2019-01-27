package pl.zajacp.controller.admin;

import pl.zajacp.model.Exercise;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/ManageExercises")

public class ServletAdminExercises extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String description = request.getParameter("description");

        Integer id = Integer.parseInt(request.getParameter("id"));

        Exercise ex = Exercise.loadById(id);
        ex.setTitle(title);
        ex.setDescription(description);

        if (ex.save()) {
            request.setAttribute("update", "complete");
        } else {
            request.setAttribute("update", "failed");
        }

        List<Exercise> exercises = Arrays.asList(Exercise.loadAll());
        request.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/jsp/manageExercises.jsp").forward(request, response);

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
            Exercise ex = Exercise.loadById(id);

            if ("delete".equals(action) && ex != null) {
                if (ex.delete()) {
                    request.setAttribute("delete", "complete");
                } else {
                    request.setAttribute("delete", "failed");
                }

            } else if ("modify".equals(action) && ex != null) {
                request.setAttribute("modified", ex);
                getServletContext().getRequestDispatcher("/jsp/manageExercisesModify.jsp").forward(request, response);
                return;
            }
        }

        List<Exercise> exercises = Arrays.asList(Exercise.loadAll());
        request.setAttribute("exercises", exercises);
        getServletContext().getRequestDispatcher("/jsp/manageExercises.jsp").forward(request, response);
    }

}
