package controllers.client;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;
import utils.DBUtil;

/**
 * Servlet implementation class ClientEditServlet
 */
@WebServlet("/client/edit")
public class ClientEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Client c = em.find(Client.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("client" , c);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("client_id", c.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/clients/edit.jsp");
        rd.forward(request, response);
    }

}
