package com.hspedu.res_system_project.dom;

import com.hspedu.res_system_project.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.util.List;

public class BasicDAO<T> {


    private QueryRunner qr = new QueryRunner();

    //insert
    public int insert(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.connection();
            return qr.update(connection, sql, params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(null, null, connection);
        }
    }

    public <T> Object insert(String sql, Class<T> tClass) {
        Connection connection = null;
        try {
            connection = JdbcUtils.connection();
            return qr.insert(connection, sql, new BeanHandler<>(tClass));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(null,null,connection);
        }
    }



    //update
    public int update(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JdbcUtils.connection();
            int update = qr.update(connection, sql, params);
            return update;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(null,null,connection);
        }
    }

    public List<T> queryMultiple(String sql, Class<T> tClass, Object... params){
        Connection connection = null;
        try {
            connection = JdbcUtils.connection();
            return qr.query(connection,sql,new BeanListHandler<>(tClass),params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtils.close(null,null,connection);
        }
    }

}
