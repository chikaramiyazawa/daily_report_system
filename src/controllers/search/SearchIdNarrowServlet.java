package controllers.search;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Searcher;
import utils.DBUtil;

/**
 * Servlet implementation class SearchIdNarrowServlet
 */
@WebServlet("/search/narrow")
public class SearchIdNarrowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdNarrowServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/narrow.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Boolean check_result = false;
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())){


        String search_id = request.getParameter("search_id");

        Searcher s = null;

        if(search_id != null && !search_id.equals("")){
            EntityManager em = DBUtil.createEntityManager();


            try{
                s = em.createNamedQuery("checkSearchUse" , Searcher.class)
                                    .setParameter("search_id", search_id)
                                    .getSingleResult();
            }catch(NoResultException ex){
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("hasError", true);
                request.setAttribute("search_id", search_id);
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/narrow.jsp");
                rd.forward(request, response);
            }

            if(s != null){
                check_result = true;
            }

        }
        if(!check_result){
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("search_id", search_id);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/narrow.jsp");
            rd.forward(request, response);
        }else{

            request.getSession().setAttribute("use", s);
            response.sendRedirect(request.getContextPath() + "/opportunity/search");
        }
    }
    }
}

