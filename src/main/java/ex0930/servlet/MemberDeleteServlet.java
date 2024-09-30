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
import java.util.List;

@WebServlet("/delete")
public class MemberDeleteServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemberDAO memberDao = new MemberDAOImpl();
        int result=0;

        String id=req.getParameter("id");
        result=memberDao.delete(id);

        if(result>0)
        {
            // 원래 리다이렉션 -> 데이터 묶는게 포워드가 더 편해서 포워드로 변경
//            List<MemberDTO> memberList=memberDao.selectAll();
//            req.setAttribute("memberList", memberList);
//            req.getRequestDispatcher("/memberSelect.jsp").forward(req, resp);
            resp.sendRedirect(req.getContextPath() +"/selectAll");
        }
        else
        {
            // 포워드
            req.setAttribute("errMsg","아이디를 삭제할 수 없습니다");
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }

    }
}
