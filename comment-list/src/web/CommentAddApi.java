package web;

import bean.Comment;
import com.google.gson.Gson;
import dao.CommentDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/add")
public class CommentAddApi extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String author = req.getParameter("author");
        String body = req.getParameter("body");
        try {
            Comment comment = new CommentDAO().addComment(new Comment(author,body));
            String json = new Gson().toJson(comment);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("不好意思，添加错误");
        }
    }
}
