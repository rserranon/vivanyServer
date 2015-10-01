import java.util.Enumeration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import org.springframework.web.filter.OncePerRequestFilter;

//@Priority(Integer.MIN_VALUE)
public class CorsFilter extends OncePerRequestFilter {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public CorsFilter() { }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        String origin = req.getHeader("Origin");
        log.info("origin " + origin);
        boolean options = "OPTIONS".equals(req.getMethod());

        Enumeration<String> headerNames = req.getHeaderNames();

        while (headerNames.hasMoreElements()) {
        	String headerName = headerNames.nextElement();
        	log.info("Header Name: "+ headerName);
        	Enumeration<String> headers = req.getHeaders(headerName);

            while (headers.hasMoreElements()) {
                String headerValue = headers.nextElement();
                log.info("Header Value: " + headerValue);
            }

        }	
            

        if (options) {
            if (origin == null) return;
            resp.addHeader("Access-Control-Allow-Headers", "origin, authorization, accept, content-type, x-requested-with");
            resp.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS");
            resp.addHeader("Access-Control-Max-Age", "3600");
        }

        resp.addHeader("Access-Control-Allow-Origin", origin == null ? "*" : origin);
        resp.addHeader("Access-Control-Allow-Credentials", "true");

        if (!options) chain.doFilter(req, resp);
    }
}