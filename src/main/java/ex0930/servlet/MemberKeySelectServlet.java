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
import java.util.List;

@WebServlet("/search")
public class MemberKeySelectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyField=req.getParameter("keyField");
        String keyWord=req.getParameter("keyWord");

        MemberDAO memberDAO=new MemberDAOImpl();
        List<MemberDTO> memberList=memberDAO.findBykeyFieldWord(keyField,keyWord);

        req.setAttribute("memberList",memberList);
        req.getRequestDispatcher("/memberSelect.jsp").forward(req, resp);
    }
}
