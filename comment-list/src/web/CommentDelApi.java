package web;

import dao.CommentDAO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/comment/del")
public class CommentDelApi extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            new CommentDAO().delete(id);
            //resp.getWriter().print(id);
        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().print("不好意思，删除失败...");
        }
    }
}

