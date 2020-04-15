package com.li.jdbctemplate;
import com.li.domain.Emp;
import com.li.jdbcDruid.JdbcUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDemo {
    //Junit单元测试
    /*
    1.修改
     */
 @Test
    public void test1(){
     //1.获取JDBCTemplate对象
     JdbcTemplate template =new JdbcTemplate(JdbcUtils.getDataSource());
     //2.定义sql
     String sql ="update product set price=? where id=?";
    //3.执行sql
     int update = template.update(sql, 1000, 1);
     System.out.println(update);
 }

    /**
     * 插入
     */
 @Test
    public void test2(){
     JdbcTemplate template =new JdbcTemplate(JdbcUtils.getDataSource());
     String sql ="insert into product values(null,?,?)";
     int update = template.update(sql, "电磁炉", "390");
     System.out.println(update);
 }
    /**
     * 删除
     */
    @Test
    public void test3(){
        JdbcTemplate template =new JdbcTemplate(JdbcUtils.getDataSource());
        String sql ="delete from product where id=?";
        int update = template.update(sql, 9);
        System.out.println(update);
    }
    /**
     * 查询product,分装成Map
     */
    @Test
    public void test4(){
        JdbcTemplate template =new JdbcTemplate(JdbcUtils.getDataSource());
        String sql ="select * from product where id=?";
        Map<String, Object> map = template.queryForMap(sql,1);
        System.out.println(map);

    }
    /**
     * 查询product,分装成List集合
     */
    @Test
    public void test5(){
        JdbcTemplate template =new JdbcTemplate(JdbcUtils.getDataSource());
        String sql ="select * from product";
        List<Map<String, Object>> maps = template.queryForList(sql);
        for (Map<String, Object> map:maps)
        {
            System.out.println(map);
        }

    }
    /**
     * 查询所有记录，将其分装到Emp对象的List集合中
     */
    @Test
    public void test6(){
        JdbcTemplate template =new JdbcTemplate(JdbcUtils.getDataSource());
        String sql ="select * from product";
       List<Emp> list= template.query(sql, new RowMapper<Emp>() {
            @Override
            public Emp mapRow(ResultSet rs, int i) throws SQLException {
                Emp emp =new Emp();
                int id=rs.getInt("id");
                String pname=rs.getString("pname");
                String price =rs.getString("price");
                emp.setId(id);emp.setPname(pname);emp.setPrice(price);
                return emp;
            }
        });
       for(Emp emp1:list )
       {
           System.out.println(emp1);
       }
    }
    /**
     * 查询所有记录，将其分装到Emp对象的List集合中
     */
    @Test
    public void test6_2() {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select * from product";
        List<Emp> query = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for(Emp emp:query){
            System.out.println(emp.getPname());
        }
    }
    /**
     * 查询总记录数
     */
    @Test
    public void test7() {
        JdbcTemplate template = new JdbcTemplate(JdbcUtils.getDataSource());
        String sql = "select count(id) from product";
        Long aLong = template.queryForObject(sql, Long.class);
        System.out.println(aLong);

    }
}
