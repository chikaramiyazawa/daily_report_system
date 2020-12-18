package controllers.opportunity;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;
import models.Opportunity;
import utils.DBUtil;

/**
 * Servlet implementation class OpportunityEditServlet
 */
@WebServlet("/opportunity/edit")
public class OpportunityEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Opportunity o = em.find(Opportunity.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        Client authorization_client = (Client)request.getSession().getAttribute("authorization_client");
        if(o != null && authorization_client.getId() == o.getClient().getId());{
        request.setAttribute("opportunity", o);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("opportunity_id", o.getId());
    }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/edit.jsp");
        rd.forward(request, response);
    }
}
