package com.mkbrv.blog.model;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

/**
 * Created by mkbrv on 22/02/2017.
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class HomePage {


    @Self
    SlingHttpServletRequest request;


    public String getUser() {
        return request.getUserPrincipal().getName();
    }


    public boolean isAuthenticated() {
        return request.getAuthType() != null;
    }


}
