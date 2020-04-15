package com.li.jdbcDruid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Druid:数据库连接池实现技术，由阿里巴巴提供
 * Druid连接池的工具类
 * 1.导入jar包
 * 2.定义配置文件
 */
public class JdbcUtils {
      //3.定义成员变量DataSource
    private static DataSource ds;
    private static Properties pro=null;
    private static ClassLoader classLoader=null;
    private static InputStream  is=null;
    static {
        try {
            //1.加载配置文件
            pro =new Properties();
            classLoader= JdbcUtils.class.getClassLoader();
            is= classLoader.getResourceAsStream("druid.properties");
            pro.load(is);
            ds= DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*获取连接*/
    public static Connection getConnection() throws SQLException {
        return  ds.getConnection();
    }
    /*释放资源*/
    public static void close(Statement stmt,Connection conn)
    {
//        if(stmt!=null)
//        {
//            try {
//                stmt.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if(conn!=null)
//        {
//            try {
//                conn.close();//归还连接
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        close(null,stmt,conn);
    }
    public static void close(ResultSet rs,Statement stmt, Connection conn)
    {
        if(rs!=null)
        {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stmt!=null)
        {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn!=null)
        {
            try {
                conn.close();//归还连接
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /*获取连接池*/
    public static  DataSource getDataSource(){
        return  ds;
    }
}
