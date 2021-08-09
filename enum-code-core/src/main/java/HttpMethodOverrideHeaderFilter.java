import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

/**
 * @author caotc
 * @date 2019-08-07
 * @since 1.0.0
 */
@Component
public class HttpMethodOverrideHeaderFilter extends OncePerRequestFilter {
    private static final String X_HTTP_METHOD_OVERRIDE_HEADER = "X-HTTP-Method-Override";

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return !RequestMethod.POST.name().equals(request.getMethod()) || Objects.isNull(request.getHeader(X_HTTP_METHOD_OVERRIDE_HEADER));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String headerValue = request.getHeader(X_HTTP_METHOD_OVERRIDE_HEADER);
        String method = headerValue.toUpperCase(Locale.ENGLISH);
        HttpServletRequest wrapper = new HttpMethodRequestWrapper(request, method);
        filterChain.doFilter(wrapper, response);
    }

    private static class HttpMethodRequestWrapper extends HttpServletRequestWrapper {
        private final String method;

        public HttpMethodRequestWrapper(HttpServletRequest request, String method) {
            super(request);
            this.method = method;
        }

        @Override
        public String getMethod() {
            return this.method;
        }
    }
}