package controllers.reports;

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

import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsEmployeeSearchServlet
 */
@WebServlet("/reports/employee/search")
public class ReportsEmployeeSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsEmployeeSearchServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/employee_search.jsp");
        rd.forward(request, response);
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();



        String name = request.getParameter("name");





        List<Report> reports = null;

        int page;

     try{
         page = Integer.parseInt(request.getParameter("page"));
     }catch(Exception e){
         page = 1;
     }

     try{
         reports = em.createNamedQuery("getNameSearch" , Report.class)
                             .setParameter("name","%" + name + "%")
                             .setFirstResult(15 * (page - 1))
                             .setMaxResults(15)
                             .getResultList();
     }catch(NoResultException ex){
         request.setAttribute("_token", request.getSession().getId());
         request.setAttribute("hasError", true);
         request.setAttribute("name", name);
         RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/employee_search.jsp");
         rd.forward(request, response);
         }
     long reports_count = (long)em.createNamedQuery("getNameSearchCount" , Long.class)
             .setParameter("name", name)
             .getSingleResult();
     em.close();


     request.setAttribute("reports", reports);
     request.setAttribute("reports_count", reports_count);
     request.setAttribute("page", page);
     if(request.getSession().getAttribute("flush")!= null){
         request.setAttribute("flush", request.getSession().getAttribute("flush"));
         request.getSession().removeAttribute("flush");
     }

 RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/employee_index.jsp");
 rd.forward(request, response);

 }
}