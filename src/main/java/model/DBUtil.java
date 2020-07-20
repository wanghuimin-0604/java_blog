package model;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:wanghuimin
 * Date:2020-07-13
 * Time:21:11
 * 一万年太久，只争朝夕，加油
 */
//管理数据库连接的类
    //建立连接
    //断开连接
public class DBUtil {
    private static volatile DataSource dataSource=null;
    private static final String URL="jdbc:mysql://127.0.0.1:3306/java_blog?characterEncoding=utf-8&useSSL=true";
    private static final String User="root";
    private static final String Password="bitekeji";


    public static DataSource getDataSource(){
        if(dataSource==null) {
            synchronized (DBUtil.class) {
                if (dataSource == null) {
                    dataSource=new MysqlDataSource();
                    ((MysqlDataSource)dataSource).setURL(URL);
                    ((MysqlDataSource)dataSource).setUser(User);
                    ((MysqlDataSource)dataSource).setPassword(Password);

                }
            }
        }
            return  dataSource;
        }
        //获取连接的方法
    public static Connection getConnection(){
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //关闭连接
    public static void close(Connection connection,PreparedStatement preparedStatement,
                             ResultSet resultSet){
        try {
            if(resultSet!=null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(preparedStatement!=null) {
            preparedStatement.close();
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(connection!=null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
