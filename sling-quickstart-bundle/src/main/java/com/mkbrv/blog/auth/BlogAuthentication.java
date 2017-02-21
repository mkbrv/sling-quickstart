package com.mkbrv.blog.auth;


import org.apache.sling.auth.core.spi.AuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationInfo;
import org.osgi.service.component.annotations.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mkbrv on 20/02/2017.
 */
@Component(service = AuthenticationHandler.class,
        immediate = true,
        property = {
                AuthenticationHandler.PATH_PROPERTY + "=/content"
        })
public class BlogAuthentication implements AuthenticationHandler {


    @Override
    public AuthenticationInfo extractCredentials(final HttpServletRequest request,
                                                 final HttpServletResponse response) {


        String username = request.getParameter("j_username");
        String pwd = request.getParameter("j_password");

        return new AuthenticationInfo("something", "hello", "pwd".toCharArray());
    }

    @Override
    public boolean requestCredentials(final HttpServletRequest request,
                                      final HttpServletResponse response) throws IOException {
        String username = request.getParameter("j_username");
        String pwd = request.getParameter("j_password");

        return false;
    }

    @Override
    public void dropCredentials(final HttpServletRequest httpServletRequest,
                                final HttpServletResponse httpServletResponse) throws IOException {

    }
}
