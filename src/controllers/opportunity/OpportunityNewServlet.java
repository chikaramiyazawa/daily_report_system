package controllers.opportunity;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Opportunity;

/**
 * Servlet implementation class OpportunityNewServlet
 */
@WebServlet("/opportunity/new")
public class OpportunityNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpportunityNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setAttribute("_token", request.getSession().getId());

        Opportunity o = new Opportunity();
        o.setOpportunity_date(new Date(System.currentTimeMillis()));
        request.setAttribute("opportunity", o);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/ops/new.jsp");
        rd.forward(request, response);
    }

}
