package com.li.jdbctemplate;

import com.li.jdbcDruid.JdbcUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * JdbcTemplate
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //1.导入jar包
        //2.创建JdbcTemplate对象
        JdbcTemplate template=new JdbcTemplate(JdbcUtils.getDataSource());
        //3.调用方法
        String sql ="update product set price=? where id=?";
        int update = template.update(sql,"500",3);
        System.out.println(update);
    }

}
