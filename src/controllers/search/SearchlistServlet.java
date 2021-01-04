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
import utils.DBUtil;

/**
 * Servlet implementation class SearchlistServlet
 */
@WebServlet("/search/list")
public class SearchlistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchlistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        int page = 1;
        try{
            page = Integer.parseInt(request.getParameter("page"));

        }catch(NumberFormatException e){}
        List<Searcher> searcher = em.createNamedQuery("getAllSearcher", Searcher.class)
                                                    .setFirstResult(15 * (page - 1))
                                                    .setMaxResults(15)
                                                    .getResultList();

        long search_count = (long)em.createNamedQuery("getSearcherCount" , Long.class)
                                                        .getSingleResult();
        em.close();

        request.setAttribute("searcher", searcher);
        request.setAttribute("search_count", search_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush",request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/Id_list.jsp");
        rd.forward(request, response);
    }
    }


