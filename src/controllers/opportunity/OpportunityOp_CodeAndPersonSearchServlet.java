package controllers.opportunity;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Opportunity;
import utils.DBUtil;

/**
 * Servlet implementation class OpportunityOp_CodeAndPresonSearchServlet
 */
@WebServlet("/opportunity/op_code/person/search")
public class OpportunityOp_CodeAndPersonSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityOp_CodeAndPersonSearchServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/cp_search.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        String op_code = request.getParameter("op_code");
        String person = request.getParameter("person");

        List<Opportunity> opportunity = null;

           int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }

        try{
            opportunity = em.createNamedQuery("getPersonAndCode" , Opportunity.class)
                                .setParameter("op_code", op_code)
                                .setParameter("person",  "%" + person + "%" )
                                .setFirstResult(15 * (page - 1))
                                .setMaxResults(15)
                                .getResultList();
        }catch(NoResultException ex){
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("op_code", op_code);
            request.setAttribute("person", person);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/cp_search.jsp");
            rd.forward(request, response);
            }
        long opportunity_count = (long)em.createNamedQuery("getPersonAndCodeCount" , Long.class)
                .setParameter("op_code", op_code)
                .setParameter("person", person)
                .getSingleResult();
        em.close();


        request.setAttribute("opportunity", opportunity);
        request.setAttribute("opportunity_count", opportunity_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush")!= null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/cp_index.jsp");
    rd.forward(request, response);

    }
}



