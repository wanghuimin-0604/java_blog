package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:wanghuimin
 * Date:2020-07-15
 * Time:19:08
 * 一万年太久，只争朝夕，加油
 */
public class ArticleDao {
    /**新增文章
       查看文章：查看文章列表
               查看指定文章的详情
       删除文章
     */
    public void add(Article article){
        Connection connection=DBUtil.getConnection();
        String sql="insert into Article values(null,?,?,?)";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getContent());
            statement.setInt(3,article.getUserId());
            int ret=statement.executeUpdate();
            if(ret!=1){
                System.out.println("插入文章失败");
                return;
            }
                System.out.println("插入文章成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(connection,statement,null);
        }
    }

    // 根据 id 找到对应的 Article 对象
    public Article selectById(int articleId) {
        // 1. 获取连接
        Connection connection = DBUtil.getConnection();
        // 2. 拼装 SQL
        // 此处有个 bug, select 和 from 之间没写 *
        // String sql = "select from article where id = ?";
        String sql = "select * from article where articleId = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, articleId);
            // 3. 执行 SQL
            resultSet = statement.executeQuery();
            // 4. 遍历结果集(id 是主键, 一定是唯一)
            if (resultSet.next()) {
                Article article = new Article();
                article.setArticleId(resultSet.getInt("articleId"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(resultSet.getInt("userId"));
                return article;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. 关闭连接
            DBUtil.close(connection, statement, resultSet);
        }
        return null;
    }

    public List<Article> selectAll(){
        List<Article> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select articleId,title,userId from Article";
        PreparedStatement statement=null;
        ResultSet rs=null;
        try {
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()) {
                Article article = new Article();
                article.setArticleId(rs.getInt("articleId"));
                article.setTitle(rs.getString("title"));
                article.setUserId(rs.getInt("userId"));
                list.add(article);
            }
                return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(connection,statement,rs);
        }
        return null;
    }

    public List<Article> selectContent(){
        List<Article> list=new ArrayList<>();
        Connection connection=DBUtil.getConnection();
        String sql="select * from Article where userId=?";
        PreparedStatement statement=null;
        ResultSet rs=null;
        try {
            statement=connection.prepareStatement(sql);
            rs=statement.executeQuery();
            while(rs.next()){
                Article article=new Article();
                article.setArticleId(rs.getInt("articleId"));
                article.setTitle(rs.getString("title"));
                article.setUserId(rs.getInt("userId"));
                list.add(article);
                return list;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(connection,statement,rs);
        }
        return null;
    }

    public void delete(int articleId){
        Connection connection=DBUtil.getConnection();
        String sql="delete from Article where articleId=?";
        PreparedStatement statement=null;
        try {
            statement=connection.prepareStatement(sql);
            statement.setInt(1,articleId);
            int ret=statement.executeUpdate();
            if(ret!=1){
                System.out.println("删除文章成功");
                return;
            }
                System.out.println("删除文章失败");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DBUtil.close(connection,statement,null);
        }

    }

    public static void main(String[] args) {
        ArticleDao articleDao=new ArticleDao();
        /*Article article=new Article();
        article.setTitle("sing");
        article.setContent("i am zhengwen");
        article.setUserId(1);
        articleDao.add(article);*/


        // 2. 验证查找文章
        /*Article article = articleDao.selectById(1);
        System.out.println(article);*/

        // 3. 验证查找所有文章
        /*List<Article> articles = articleDao.selectAll();
        System.out.println(articles);*/

        // 4. 删除指定文章
        //articleDao.delete(3);
    }
}
