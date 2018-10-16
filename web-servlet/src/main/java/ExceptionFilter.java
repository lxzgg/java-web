import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ExceptionFilter")
public class ExceptionFilter implements Filter {

    private static ObjectMapper mapper = new ObjectMapper();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        var startTime = System.currentTimeMillis();
        var response = (HttpServletResponse) resp;
        try {
            chain.doFilter(req, resp);
        } catch (ErrorException e) {
            ObjectNode node = mapper.createObjectNode();
            node.put("code", e.getCode());
            node.put("message", e.getMessage());

            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(node.toString());
        }
        var endTime = System.currentTimeMillis();
        response.addHeader("X-Response-Time", String.valueOf(endTime - startTime));
    }

}
