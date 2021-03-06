package controllers.opportunity;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class OpportunityIndexServlet
 */
@WebServlet("/opportunity/index")
public class OpportunityIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Client authorization_client = (Client)request.getSession().getAttribute("authorization_client");

        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        List<Opportunity>opportunity = em.createNamedQuery("getMyClientOpportunity",Opportunity.class)
                .setParameter("client", authorization_client)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long opportunity_count = (long)em.createNamedQuery("getMyClientOpportunityCount" , Long.class)
                .setParameter("client", authorization_client)
                .getSingleResult();
        em.close();

        request.setAttribute("opportunity", opportunity);
        request.setAttribute("opportunity_count", opportunity_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush")!= null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/index.jsp");
         rd.forward(request, response);
    }

}
