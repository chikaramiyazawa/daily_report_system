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

import models.Opportunity;
import models.Searcher;
import utils.DBUtil;

/**
 * Servlet implementation class OppotunitySearchIndexServlet
 */
@WebServlet("/opportunity/search")
public class OpportunitySearchIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunitySearchIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Searcher use = (Searcher)request.getSession().getAttribute("use");

        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        List<Opportunity>opportunity = em.createNamedQuery("getMySearch_idOpportunity",Opportunity.class)
                .setParameter("search_id", use)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long opportunity_count = (long)em.createNamedQuery("getMySearch_idOpportunityCount" , Long.class)
                .setParameter("search_id", use)
                .getSingleResult();
        em.close();

        request.setAttribute("opportunity", opportunity);
        request.setAttribute("opportunity_count", opportunity_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush")!= null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/narrow_index.jsp");
         rd.forward(request, response);
    }


    }


