package indi.tom.test.JDBCtest;

import java.sql.*;

/**
 * @Author totian
 * @Date 2019/11/6 20:07
 * @Version 1.0
 * @Description
 */
public class Test01 {
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc??useUnicode=true&characterEncoding=utf8","root","Dianshi.123");
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if(rs != null){
            rs.close();
        }
        if(ps != null){
            ps.close();
        }
        if(conn != null){
            conn.close();
        }

    }

    public static void main(String[] args) throws SQLException {
/*        //test01
        Connection conn = getConnection();
        conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        for (int i = 0; i < 2000; i++) {
            stmt.addBatch("insert into student values(default,'tom" + i + "','123', now())" );
        }
        stmt.executeBatch();
        conn.commit();
        close(conn, null, null);
        stmt.close();*/
        //test02
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("select * from student where id = ?");
        ps.setObject(1, 13);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getInt(1) + "---" + rs.getString(2)+ "---" +
                    rs.getString(3));
        }
    }

}
