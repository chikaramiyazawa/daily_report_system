package controllers.opportunity;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Opportunity;
import models.validators.OpportunityValidator;
import utils.DBUtil;

/**
 * Servlet implementation class OpportunityUpdateServlet
 */
@WebServlet("/opportunity/update")
public class OpportunityUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token = (String)request.getParameter("_token");


        if(_token != null && _token.equals(request.getSession().getId()));
        EntityManager em = DBUtil.createEntityManager();

        Opportunity o = em.find(Opportunity.class, (Integer)(request.getSession().getAttribute("opportunity_id")));

        o.setOpportunity_date(Date.valueOf(request.getParameter("opportunity_date")));
        o.setOpportunity(request.getParameter("opportunity"));
        o.setStatus(request.getParameter("status"));
        o.setUpdated_at(new Timestamp(System.currentTimeMillis()));

        List<String>errors = OpportunityValidator.validate(o);
        if(errors.size() > 0){
            em.close();

            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("opportunity", o);
            request.setAttribute("errors", errors);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/edit.jsp");
            rd.forward(request, response);
        }else{
            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();
            request.getSession().setAttribute("flush", "更新が完了しました。");
            request.getSession().removeAttribute("opportunity_id");
            response.sendRedirect(request.getContextPath() + "/opportunity/index");
        }
    }

}
