import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "ErrorFilter")
public class ErrorFilter implements Filter {

    private static ObjectMapper mapper = new ObjectMapper();

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        var startTime = System.currentTimeMillis();
        var response = (HttpServletResponse) resp;
        try {
            chain.doFilter(req, resp);
        } catch (ErrorException e) {
            response.reset();
            ObjectNode node = mapper.createObjectNode();
            node.put("code", e.getCode());
            node.put("message", e.getMessage());

            response.setContentType("application/json;charset=UTF-8");
            response.addDateHeader("X-Response-Time", System.currentTimeMillis() - startTime);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(node.toString());
        }

    }

}
