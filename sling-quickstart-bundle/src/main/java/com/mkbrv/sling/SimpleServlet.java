package com.mkbrv.sling;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by mkbrv on 23/11/16.
 */
@SlingServlet(paths = "/bin/sample", methods = "GET")
public class SimpleServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        response.getWriter().println("Hello World!");
    }
}
