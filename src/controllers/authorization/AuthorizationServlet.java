package controllers.authorization;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class AuthorizationServlet
 */
@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorizationServlet() {
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
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/authorization/authorization.jsp");
        rd.forward(request, response);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Boolean check_result = false;

        String companycode = request.getParameter("companycode");
        String companyname = request.getParameter("companyname");
        String plain_pass = request.getParameter("password");

        Client c = null;

        if(companycode != null && !companycode.equals("") && companyname != null && !companyname.equals("") && plain_pass != null && !plain_pass.equals("")){
            EntityManager em = DBUtil.createEntityManager();

            String password = EncryptUtil.getPasswordEncrypt(plain_pass,
                                        (String)this.getServletContext().getAttribute("pepper"));
            try{
                c = em.createNamedQuery("checkAuthentizationCodeAndPassword" , Client.class)
                                    .setParameter("companycode", companycode)
                                    .setParameter("password", password)
                                    .getSingleResult();
            }catch(NoResultException ex){}

            em.close();

            if(c != null){
                check_result = true;
            }
        }
        if(!check_result){
            request.setAttribute("_token", request.getSession().getId());
            request.setAttribute("hasError", true);
            request.setAttribute("companycode", companycode);
            request.setAttribute("companyname", companyname);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/authorization/authorization.jsp");
            rd.forward(request, response);
        }else{
            request.getSession().setAttribute("authorization_client", c);
            request.getSession().setAttribute("authorization_companycode" , c);
            request.getSession().setAttribute("flush", "認証完了しました。");
            response.sendRedirect(request.getContextPath() + "/opportunity/index");
        }
    }

}
