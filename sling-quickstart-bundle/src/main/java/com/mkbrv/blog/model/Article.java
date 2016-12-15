package com.mkbrv.blog.model;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mkbrv on 14/12/16.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Article {

    @Self
    private Resource article;

    @Inject
    private String title;

    @Inject
    @Optional
    private String image;

    @Inject
    private String text;

    @Inject
    private String teaser;

    @Inject
    @Named("jcr:created")
    private Date date;

    @Inject
    @Named("jcr:createdBy")
    private String author;


    public List<Comment> getComments() {
        List<Resource> comments = new ArrayList<>();
        article.getChildren().forEach(comments::add);
        return comments.stream()
                .filter(resource -> resource.getResourceType().equals("blog/components/comment"))
                .map(resource -> resource.adaptTo(Comment.class))
                .sorted((o1, o2) -> o2.getTimestamp().compareTo(o1.getTimestamp()))
                .collect(Collectors.toList());
    }

    public String getPath() {
        return article.getPath();
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        if (image == null && this.article.getChild("imageFile") != null) {
            image = this.article.getChild("imageFile").getPath();
        }
        return image;
    }

    public String getText() {
        return text;
    }

    public String getTeaser() {
        return teaser;
    }

    public String getDate() {
        return date.toString();
    }

    public String getAuthor() {
        return author;
    }
}
