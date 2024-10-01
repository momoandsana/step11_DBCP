package ex0930.dao;

import ex0930.dto.MemberDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO {
    @Override
    public List<MemberDTO> selectAll() {
        List<MemberDTO> list = new ArrayList<MemberDTO>();
        String query="select id,pwd,name,age,phone,addr,join_date from member";
//        String query="select id,pwd,name,age,phone,addr,join_date from member order by join_date desc";

        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try
        {
            con=DBManager.getConnection();
            pstmt=con.prepareStatement(query);
            rs= pstmt.executeQuery();

            while(rs.next())
            {
                MemberDTO member=new MemberDTO();
                member.setId(rs.getString("id"));
                member.setPwd(rs.getString("pwd"));
                member.setName(rs.getString("name"));
                member.setAge(rs.getString("age"));
                member.setPhone(rs.getString("phone"));
                member.setAddr(rs.getString("addr"));
                member.setJoinDate(rs.getString("join_date"));
                list.add(member);
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBManager.dbClose(con,pstmt,rs);
        }
        return list;
    }

    @Override
    public int insert(MemberDTO memberDTO)
    {
        String query="insert into member(id,pwd,name,age,phone,addr,join_date) values(?,?,?,?,?,?,sysdate)";
        Connection con=null;
        PreparedStatement pstmt=null;
        int result=0;

        try
        {
            con=DBManager.getConnection();
            pstmt=con.prepareStatement(query);

            pstmt.setString(1,memberDTO.getId());
            pstmt.setString(2,memberDTO.getPwd());
            pstmt.setString(3,memberDTO.getName());
            pstmt.setString(4,memberDTO.getAge());
            pstmt.setString(5,memberDTO.getPhone());
            pstmt.setString(6,memberDTO.getAddr());

            result=pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            DBManager.dbClose(con,pstmt);
        }

        return result;
    }

    @Override
    public MemberDTO getSelectById(String id) {
        MemberDTO member=null;

        String query="select id,pwd,name,age,phone,addr,join_date from member where id=?";
        Connection con=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        try
        {
            con=DBManager.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,id);
            rs= pstmt.executeQuery();

            if(rs.next())
            {
                member=new MemberDTO();
                member.setId(rs.getString("id"));
                member.setPwd(rs.getString("pwd"));
                member.setName(rs.getString("name"));
                member.setAge(rs.getString("age"));
                member.setPhone(rs.getString("phone"));
                member.setAddr(rs.getString("addr"));
                member.setJoinDate(rs.getString("join_date"));
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBManager.dbClose(con,pstmt,rs);
        }
        return member;
    }

    @Override
    public int delete(String id) {
        String query="delete from member where id=?";

        Connection con=null;
        PreparedStatement pstmt=null;
        int result=0;

        try
        {
            con=DBManager.getConnection();
            pstmt=con.prepareStatement(query);
            pstmt.setString(1,id);

            result=pstmt.executeUpdate();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBManager.dbClose(con,pstmt);
        }


        return result;
    }

    @Override
    public int update(MemberDTO memberDTO) {
        return 0;
    }

    @Override
    public List<MemberDTO> findBykeyFieldWord(String keyField, String keyWord) {
        List<MemberDTO> members = new ArrayList<>();
        String query = "SELECT id, pwd, name, age, phone, addr, join_date FROM member WHERE " + keyField + " LIKE ?";
//        여기서 keyField 는 id, name, addr

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = DBManager.getConnection();
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, "%" + keyWord + "%");  // 앞, 뒤로 부분 일치 검색

            rs = pstmt.executeQuery();

            while (rs.next())
            {
                MemberDTO member = new MemberDTO();
                member.setId(rs.getString("id"));
                member.setPwd(rs.getString("pwd"));
                member.setName(rs.getString("name"));
                member.setAge(rs.getString("age"));
                member.setPhone(rs.getString("phone"));
                member.setAddr(rs.getString("addr"));
                member.setJoinDate(rs.getString("join_date"));
                members.add(member);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBManager.dbClose(con, pstmt, rs);
        }

        return members;
    }

}
