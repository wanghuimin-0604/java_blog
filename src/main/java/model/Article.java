package model;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User:wanghuimin
 * Date:2020-07-13
 * Time:22:03
 * 一万年太久，只争朝夕，加油
 */
public class Article {
    private int articleId;
    private String title;
    private String content;
    private int userId;

    public Article(int articleId, String title, String content, int userId) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public Article() {
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "article{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", uerId=" + userId +
                '}';
    }
}
