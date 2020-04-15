package com.li.druidTest;

import com.li.jdbcDruid.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 测试DruidUtils
 */
public class DruidUtilsTest {
    public static void main(String[] args) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        /*完成添加操作*/
        try {
            //1.获取连接
            conn = JdbcUtils.getConnection();
            //2.定义sql
            String sql ="insert into product values(null,?,?)";
            //3.获取pstmt对象
            pstmt = conn.prepareStatement(sql);
            //4.执行sql
            pstmt.setString(1,"手机");
            pstmt.setString(2,"5555");
            int i = pstmt.executeUpdate();
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
              JdbcUtils.close(pstmt,conn);
        }
    }
}
