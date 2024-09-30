package ex0930.servlet;

import ex0930.dao.MemberDAO;
import ex0930.dao.MemberDAOImpl;
import ex0930.dto.MemberDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Member;

@WebServlet("/insert")
public class MemberInsertServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        String pwd=req.getParameter("pwd");
        String name=req.getParameter("name");
        String age=req.getParameter("age");
        String phone=req.getParameter("phone");
        String addr=req.getParameter("addr");

        MemberDTO member=new MemberDTO(id,pwd,name,age,phone,addr,null);

        MemberDAO memberDAO=new MemberDAOImpl();

        MemberDTO existingMember=memberDAO.getSelectById(id);

        int result=0;

        if(existingMember==null)
        {
            result=memberDAO.insert(member);
        }
        else
        {
           req.getSession().setAttribute("errMsg", "이미 중복된 아이디를 가진 회원이 있습니다");
//            resp.sendRedirect(req.getContextPath()+"/error.jsp");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }

        if(result>0)
        {

//            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            resp.sendRedirect("/index.jsp");
        }
        else
        {
            req.getSession().setAttribute("errMsg", "회원가입 중 오류가 발생함");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
//            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }

    }
}
