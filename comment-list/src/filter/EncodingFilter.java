package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse reps = (HttpServletResponse) response;
        reps.setHeader("Access-Control-Allow-Origin", "*");
        reps.setHeader("Access-Control-Methods", "*");
        reps.setHeader("Access-Control-Max-Age", "3600");
        reps.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        
        // 解决乱码问题
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
