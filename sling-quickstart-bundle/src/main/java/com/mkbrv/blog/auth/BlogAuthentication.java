package com.mkbrv.blog.auth;


import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.auth.core.AuthUtil;
import org.apache.sling.auth.core.spi.AuthenticationFeedbackHandler;
import org.apache.sling.auth.core.spi.AuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationInfo;
import org.apache.sling.jackrabbit.usermanager.CreateUser;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by mkbrv on 20/02/2017.
 */
@Component(service = AuthenticationHandler.class,
        immediate = true,
        property = {
                AuthenticationHandler.PATH_PROPERTY + "=/content"
        })
public class BlogAuthentication implements AuthenticationHandler, AuthenticationFeedbackHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogAuthentication.class);

    private static final String REQUEST_METHOD = "POST";
    private static final String USER_NAME = "j_username";
    private static final String PASSWORD = "j_password";
    private static final String REQUEST_URL_SUFFIX = "/j_login";
    private static final String AUTH_TYPE = "FORM";

    @Reference
    AuthenticationFeedbackHandler feedbackHandler;

    @Reference
    CreateUser createUser;

    @Reference
    ResourceResolverFactory resourceResolverFactory;


    @Override
    public AuthenticationInfo extractCredentials(final HttpServletRequest request,
                                                 final HttpServletResponse response) {
        if (REQUEST_METHOD.equals(request.getMethod()) && request.getRequestURI().endsWith(REQUEST_URL_SUFFIX)
                && request.getParameter(USER_NAME) != null) {

            if (!AuthUtil.isValidateRequest(request)) {
                AuthUtil.setLoginResourceAttribute(request, request.getContextPath());
            }

            SimpleCredentials creds = new SimpleCredentials(request.getParameter(USER_NAME), request.getParameter(PASSWORD).toCharArray());

            try (ResourceResolver resourceResolver = resourceResolverFactory.getAdministrativeResourceResolver(null)) {
                Session session = resourceResolver.adaptTo(Session.class);
                createUser.createUser(session,
                        request.getParameter(USER_NAME), request.getParameter(PASSWORD),
                        request.getParameter(PASSWORD), new HashMap<>(), new ArrayList<>());
                session.save();
            } catch (RepositoryException | LoginException e) {
                LOGGER.info("user {}  already exists", creds, e.getMessage());
            }

            return createAuthenticationInfo(creds);
        }
        return null;
    }

    /**
     * @param creds
     * @return
     */
    private AuthenticationInfo createAuthenticationInfo(final SimpleCredentials creds) {
        AuthenticationInfo info = new AuthenticationInfo(AUTH_TYPE);
        info.put("user.name", creds.getUserID());
        info.put("user.password", creds.getPassword());
        info.put("$$auth.info.login$$", new Object());
        info.put("sling.auth.redirect", "http://google.com");
        return info;
    }

    @Override
    public boolean authenticationSucceeded(HttpServletRequest request, HttpServletResponse response, AuthenticationInfo authInfo) {
        return feedbackHandler.authenticationSucceeded(request, response, authInfo);
    }

    @Override
    public void authenticationFailed(HttpServletRequest request, HttpServletResponse response, AuthenticationInfo authInfo) {
        feedbackHandler.authenticationFailed(request, response, authInfo);
    }

    @Override
    public boolean requestCredentials(final HttpServletRequest request,
                                      final HttpServletResponse response) throws IOException {
        return false;
    }

    @Override
    public void dropCredentials(final HttpServletRequest httpServletRequest,
                                final HttpServletResponse httpServletResponse) throws IOException {

    }
}
