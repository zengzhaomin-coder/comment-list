package dao;

import bean.Comment;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.DBHelper;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public class CommentDAO {
    /**
     * 查询所有信息
     * @return
     * @throws Exception
     */
    public List<Comment> getAll() throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "select id, author, body from comments";
        try {
            return new QueryRunner().query(
                    conn, sql, new BeanListHandler<Comment>(Comment.class));
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 添加信息
     * @param comment
     * @return
     * @throws Exception
     */
    public Comment addComment(Comment comment) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "insert into comments(author, body)values(?,?)";
        try {
            QueryRunner runner = new QueryRunner();
            BigDecimal res = runner.insert(conn, sql, new ScalarHandler<BigDecimal>(),comment.getAuthor(), comment.getBody());
            comment.setId(res.intValue());
            return comment;
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }

    /**
     * 删除信息
     * @param id
     * @throws Exception
     */
    public void delete(int id) throws Exception {
        Connection conn = DBHelper.getConnection();
        String sql = "delete from comments where id = ?";
        try {
            new QueryRunner().update(conn, sql, id);
        } finally {
            DbUtils.closeQuietly(conn);
        }
    }
}
