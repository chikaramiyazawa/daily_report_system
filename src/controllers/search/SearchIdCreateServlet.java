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
import models.validators.Search_idValidator;
import utils.DBUtil;


/**
 * Servlet implementation class SearchIdCreateServlet
 */
@WebServlet("/searchId/create")
public class SearchIdCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdCreateServlet() {
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

            Searcher s = new Searcher();

            s.setSearch_id(request.getParameter("search_id"));

            s.setUsed(0);

            s.setDelete_flag(0);

            List<String> errors = Search_idValidator.validate(s, true);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("searcher", s);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(s);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "新しい商談Idを作成しました。");
                em.close();
                response.sendRedirect(request.getContextPath() + "/search/list");
            }
        }
    }

}
