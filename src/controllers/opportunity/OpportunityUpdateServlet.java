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

import models.Client;
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
        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();
            Opportunity o = new Opportunity();

            o.setCompanycode((Client)request.getSession().getAttribute("authorization_companycode"));
            o.setClient((Client)request.getSession().getAttribute("authorization_client"));

            Date opportunity_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("opportunity_date");
            if(rd_str != null && !rd_str.equals("")){
                opportunity_date = Date.valueOf(request.getParameter("opportunity_date"));
            }
            o.setOpportunity_date(opportunity_date);
            o.setChanger(request.getParameter("changer"));
            o.setOpportunity(request.getParameter("opportunity"));
            o.setStatus(request.getParameter("status"));
            o.setPerson(request.getParameter("person"));
            o.setLocation(request.getParameter("location"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            o.setCreated_at(currentTime);
            o.setUpdated_at(currentTime);

            List<String>errors = OpportunityValidator.validate(o);
            if(errors.size () > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("opportunity", o);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(o);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");
                response.sendRedirect(request.getContextPath() + "/opportunity/index");
            }
        }
    }
}

