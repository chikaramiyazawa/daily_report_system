package controllers.reports;

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

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsDateSearchServlet
 */
@WebServlet("/reports/date/search")
public class ReportsDateSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsDateSearchServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/date_search.jsp");
        rd.forward(request, response);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        List<Report> reports = null;

        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page = 1;
        }

        try{
            Date report_date = Date.valueOf(request.getParameter("report_date"));
            reports = em.createNamedQuery("getReport_dateSearch" , Report.class)
                    .setParameter("report_date", report_date )
                    .setFirstResult(15 * (page - 1))
                    .setMaxResults(15)
                    .getResultList();


            long reports_count = (long)em.createNamedQuery("getReport_dateSearchCount" , Long.class)
                    .setParameter("report_date", report_date)
                    .getSingleResult();
            em.close();

            request.setAttribute("reports", reports);
            request.setAttribute("reports_count", reports_count);
            request.setAttribute("page", page);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/date_index.jsp");
            rd.forward(request, response);

        }

        catch (IllegalArgumentException e) {
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", false);
            if(request.getSession().getAttribute("flush") != null){
                request.setAttribute("flush", request.getSession().getAttribute("flush"));
                request.getSession().removeAttribute("flush");
            }
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/date_index.jsp");
            rd.forward(request, response);

        }
    }

    }

