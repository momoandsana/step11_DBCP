package ex0930.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;

/*
DBCP 연동을 위한 로드, 연결, 닫기
JNDI Datasource 방법으로 연동
java naming directory interface
 */
public class DBManager {

    private static DataSource ds;

    static
    {
        try
        {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            // JNDI Datasource 를 위한 name, 고정값
            ds = (DataSource)envContext.lookup("jdbc/myoracle");
            // context.xml 에 설정한 name 을 넣어준다, object 이기 때문에 type 에 맞게 다운캐스팅
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }// static End

    /**
     * 연결 객체 리턴해주는 메소드 작성
     *
     */
    public static Connection getConnection() throws SQLException
    {
        return ds.getConnection();
    }

    /**
     * 닫기 기능(select 인 경우)
     */
    public static void dbClose(Connection con, Statement st, ResultSet rs)
    {

        try
        {
            if(rs != null)rs.close();
            dbClose(con,st);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 닫기 기능(dml 인 경우)
     */
    public static void dbClose(Connection con, Statement st)
    {

            try
            {
                if(st!=null)st.close();
                if(con!=null)con.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
    }

}// class End
