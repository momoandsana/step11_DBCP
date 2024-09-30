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


        boolean isDuplicate=false;
        for (MemberDTO memberDTO : memberDAO.selectAll()) {
            if(memberDTO.getId().equals(id)) {
                isDuplicate=true;
                break;
            }
        }

        int result=0;
        if(!isDuplicate)
        {
            result=memberDAO.insert(member);
        }
        else
        {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().println("<script>");
            resp.getWriter().println("alert('이미 존재하는 아이디입니다.');");// 브라우저에서 빈칸으로 나옴
            resp.getWriter().println("window.location.href = '" + req.getContextPath() + "/login.jsp';");
            resp.getWriter().println("</script>");

//            resp.sendRedirect(req.getContextPath()+"/login.jsp");
//            위에 코드랑 같이 쓰면 에러 생김. 자바스크립트 쓸거면 리다이렉트도 자바스크립트로 하기
        }

        if(result>0)
        {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
        else
        {
            req.getSession().setAttribute("errMsg", "회원가입 중 오류가 발생함");
            resp.sendRedirect(req.getContextPath()+"/error.jsp");
        }

    }
}
