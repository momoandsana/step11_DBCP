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

@WebServlet("/readServlet")
public class MemberSelectByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDAO memberDao=new MemberDAOImpl();

        String id=req.getParameter("id");
        MemberDTO memberDTO=memberDao.getSelectById(id);

        if(memberDTO!=null)
        {
            // 포워드
            req.setAttribute("member", memberDTO);
            req.getRequestDispatcher("/read.jsp").forward(req, resp);
        }
        else
        {
            // 포워드
            req.setAttribute("member", "해당 아이디와 매치되는 아이디가 없습니다");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }
}
