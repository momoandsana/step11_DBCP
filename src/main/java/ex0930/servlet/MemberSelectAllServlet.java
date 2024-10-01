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

@WebServlet(urlPatterns = "/selectAll")
public class MemberSelectAllServlet extends HttpServlet {

    private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        memberDAO = new MemberDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MemberDTO> memberList = memberDAO.selectAll();
        memberList.forEach(member -> System.out.println(member.getName()));
//        여기까지 잘 넘어옴

        // 회원 목록을 request 객체에 저장
        request.setAttribute("memberList", memberList);

        // 회원 목록을 보여줄 JSP로 포워딩
        request.getRequestDispatcher("/memberSelect.jsp").forward(request, response);
    }
}
