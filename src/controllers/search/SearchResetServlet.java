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
 * Servlet implementation class SearchResetServlet
 */
@WebServlet("/search/reset")
public class SearchResetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Boolean check_result = false;

        String _reset = (String)request.getParameter("_reset");
        if(_reset != null && _reset.equals(request.getSession().getId())){

        EntityManager em = DBUtil.createEntityManager();


        Searcher s = null;

        String search_id = request.getParameter("search_id");

        try{
                        s = em.createNamedQuery("checkSearchUse" , Searcher.class)
                                            .setParameter("search_id", search_id)
                                            .getSingleResult();
                    }catch(NoResultException ex){}


                    s.setSearch_id(request.getParameter("search_id"));

                    s.setUsed(0);

                    s.setDelete_flag(0);



                    if(s != null){
                        check_result = true;
                    }


                if(!check_result){
                    request.setAttribute("_reset", request.getSession().getId());
                    request.setAttribute("hasError", true);
                    request.setAttribute("search_id", search_id);
                    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/searchs/reset.jsp");
                    rd.forward(request, response);
                }else{

                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    em.close();


                    response.sendRedirect(request.getContextPath() + "/opportunity/index");
                }
        }
    }
}

