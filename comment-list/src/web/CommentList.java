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
import java.util.List;

@WebServlet("/comment/list")
public class CommentList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Comment> Comments = new CommentDAO().getAll();
            String json = new Gson().toJson(Comments);
            resp.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("不好意思，显示错误");
        }
    }
}
