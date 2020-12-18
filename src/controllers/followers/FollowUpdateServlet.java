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

import models.Follow;
import models.validators.FollowValidator;
import utils.DBUtil;

/**
 * Servlet implementation class FollowUpdateServlet
 */
@WebServlet("/follow/update")
public class FollowUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowUpdateServlet() {
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

            Follow f = em.find(Follow.class, (Integer)(request.getSession().getAttribute("follow_id")));

            f.setReport_date(Date.valueOf(request.getParameter("report_date")));
            f.setTitle(request.getParameter("title"));
            f.setContent(request.getParameter("content"));
            f.setFollowerdname(request.getParameter("followerdname"));
            f.setReview(request.getParameter("review"));
            f.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors = FollowValidator.validate(f);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("follow", f);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/follows/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");
                request.getSession().removeAttribute("report_id");
                response.sendRedirect(request.getContextPath() + "/followers/index");
            }
        }
    }

}
