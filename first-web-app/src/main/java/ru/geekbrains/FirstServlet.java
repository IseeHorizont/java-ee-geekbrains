package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class FirstServlet implements Servlet {

    private static final Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("FirstServlet is initialized!");
        this.config = config;
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("New request to FirstServlet");
        servletResponse.getWriter().println("<h1>Hello from your first servlet!</h1>");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
