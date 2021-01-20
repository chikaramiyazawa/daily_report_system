package controllers.client;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Client;
import models.validators.ClientValidator;
import utils.DBUtil;

/**
 * Servlet implementation class ClientUpdateServlet
 */
@WebServlet("/client/update")
public class ClientUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientUpdateServlet() {
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

            Client c = em.find(Client.class, (Integer)(request.getSession().getAttribute("client_id")));

            Boolean code_duplicate_check_flag = true;
            if(c.getCompanycode().equals(request.getParameter("companycode"))){
                code_duplicate_check_flag = false;
            }else{
                c.setCompanycode(request.getParameter("companycode"));
            }


            c.setCompanyname(request.getParameter("companyname"));
            c.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            c.setDelete_flag(0);

            List<String> errors = ClientValidator.validate(c, code_duplicate_check_flag);
            if(errors.size() > 0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("client", c);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/clients/edit.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");

                request.getSession().removeAttribute("client_id");
                response.sendRedirect(request.getContextPath() + "/client/index");
            }

        }
    }

}
