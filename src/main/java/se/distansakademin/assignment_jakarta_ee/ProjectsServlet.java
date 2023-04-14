package se.distansakademin.assignment_jakarta_ee;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.distansakademin.models.Project;
import se.distansakademin.repositories.ProjectRepository;

import java.io.IOException;

@WebServlet(name = "ProjectsServlet", urlPatterns = {"/projects"})
public class ProjectsServlet extends HttpServlet {

    private ProjectRepository projectRepository;

    public ProjectsServlet() {
        projectRepository = new ProjectRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Get current action from url
        // String action = request.getParameter("action");
        // action = (action == null) ? "create" : action;

        String title = request.getParameter("title");
        String description = request.getParameter("description");


        // if (action.equals("create")) {
        var project = new Project(title, description);
        projectRepository.saveProject(project);
        // } else if(action.equals("edit")) {
        // Edit with repository
        // } else if(action.equals("delete")) {
        // Delete with repository
        // }

        response.sendRedirect(request.getContextPath() + "/projects");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "index" : action;

        if (action.equals("create")) {
            showCreateForm(request, response);
        }
        else if(action.equals("edit")){
            // showEditForm(request, response)
        }
        else {
            listProjects(request, response);
        }
    }

    private void listProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var projects = projectRepository.getAll();

        request.setAttribute("projects", projects);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/projects/index.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/projects/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // BASE_URL = http://localhost:8080/assignment_jakarta_ee_war_exploded
        // {BASE_URL}/projects?action=edit&id=X

        // Get current id
        // var idString = request.getParameter("id");
        // var id = (TO LONG)

        // Get one project
        // var project = projectRepository.getById(id)

        // Send project to view
        // request.setAttribute("project", project);

        // Show correct view
        // RequestDispatcher dispatcher = request.getRequestDispatcher("/views/projects/edit.jsp");
        // dispatcher.forward(request, response);
    }
}
