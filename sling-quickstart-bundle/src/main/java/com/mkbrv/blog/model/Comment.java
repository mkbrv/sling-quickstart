package com.mkbrv.blog.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;

/**
 * Created by mkbrv on 14/12/16.
 */
@Model(adaptables = Resource.class)
public class Comment {

    @Inject
    private String author;

    @Inject
    private String text;

    @Inject
    @Named("jcr:created")
    private Date date;

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date.toString();
    }

    public Long getTimestamp() {
        return date.getTime();
    }

    public String getText() {
        return text;
    }
}
