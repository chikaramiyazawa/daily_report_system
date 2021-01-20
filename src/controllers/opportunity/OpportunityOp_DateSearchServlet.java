package controllers.opportunity;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Opportunity;
import utils.DBUtil;

/**
 * Servlet implementation class OpportunityOp_DateSearchSearch
 */
@WebServlet("/opportunity/op_date/search")
public class OpportunityOp_DateSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityOp_DateSearchServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/date_search.jsp");
        rd.forward(request, response);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        List<Opportunity> opportunity = null;

        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }

        try{
            Date opportunity_date = Date.valueOf(request.getParameter("opportunity_date"));

            opportunity= em.createNamedQuery("getOpportunity_date" , Opportunity.class)
                    .setParameter("opportunity_date", opportunity_date )
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();


            long opportunity_count = (long)em.createNamedQuery("getOpportunity_dateCount" , Long.class)
                    .setParameter("opportunity_date", opportunity_date)
                    .getSingleResult();
            em.close();

            request.setAttribute("opportunity", opportunity);
            request.setAttribute("opportunity_count", opportunity_count);
            request.setAttribute("page", page);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/date_index.jsp");
            rd.forward(request, response);

        }

        catch (IllegalArgumentException e) {
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", false);
            if(request.getSession().getAttribute("flush") != null){
                request.setAttribute("flush", request.getSession().getAttribute("flush"));
                request.getSession().removeAttribute("flush");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/date_index.jsp");
            rd.forward(request, response);

        }
    }

    }



