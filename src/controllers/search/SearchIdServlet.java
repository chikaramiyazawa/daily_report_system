package controllers.search;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Searcher;
import utils.DBUtil;

/**
 * Servlet implementation class SearchIdServlet
 */
@WebServlet("/search/id")
public class SearchIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("hasError", false);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/searchid_form.jsp");
        rd.forward(request, response);
    }



    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Searcher search_id = (Searcher)request.getSession().getAttribute("search_id");

        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }
        List<Searcher>opportunity = em.createNamedQuery("getAllSearcher",Searcher.class)
                .setParameter("searcher", search_id)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();

        long search_id_opportunity_count = (long)em.createNamedQuery("getMySearch_idOpportunityCount" , Long.class)
                .setParameter("opportunity", search_id)
                .getSingleResult();
        em.close();

        request.setAttribute("opportunity", opportunity);
        request.setAttribute("opportunity_count", search_id_opportunity_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush")!= null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/search_id_index.jsp");
         rd.forward(request, response);
    }
 }



