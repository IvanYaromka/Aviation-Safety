package by.yaromka.aviation.safety.security.entry;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class EntryPoint extends BasicAuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse resp,
                         AuthenticationException e) throws IOException {
        resp.setContentType("application/json");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String body = String.format("{\n" +
                "  \"code\": \"401\",\n" +
                "  \"message\": [\n" +
                "    \"Invalid login or password\"\n" +
                "  ],\n" +
                "  \"timestamp\": \"%s\"\n" +
                "}", new Date());
        resp.getOutputStream().println(body);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("aviation-safety");
        super.afterPropertiesSet();
    }
}

