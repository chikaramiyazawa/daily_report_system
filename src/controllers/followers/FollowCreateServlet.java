package controllers.followers;

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

import models.Employee;
import models.Follow;
import models.validators.FollowValidator;
import utils.DBUtil;



/**
 * Servlet implementation class FollowCreateServlet
 */
@WebServlet("/follow/create")
public class FollowCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowCreateServlet() {
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
            Follow f = new Follow();

            f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

            Date report_date = new Date(System.currentTimeMillis());
            String rd_str = request.getParameter("report_date");
            if(rd_str != null && !rd_str.equals("")){
                report_date = Date.valueOf(request.getParameter("report_date"));
            }
            f.setReport_date(report_date);

            f.setTitle(request.getParameter("title"));
            f.setContent(request.getParameter("content"));
            f.setFollowerdname(request.getParameter("followerdname"));
            f.setReview(request.getParameter("review"));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            f.setCreated_at(currentTime);
            f.setUpdated_at(currentTime);

            List<String> errors = FollowValidator.validate(f);
            if(errors.size () >0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("follow", f);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(f);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush","フォローしました。");
                response.sendRedirect(request.getContextPath() + "/reports/index");
            }

        }
    }
   }


