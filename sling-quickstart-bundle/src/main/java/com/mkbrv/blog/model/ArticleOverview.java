package com.mkbrv.blog.model;

import com.mkbrv.blog.service.ArticleService;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by mkbrv on 14/12/16.
 */
@Model(adaptables = Resource.class)
public class ArticleOverview {

    @Inject
    private ArticleService articleService;

    @Self
    private Resource resource;


    public List<Article> getArticles() {
        return articleService.findLatestArticles(resource.getResourceResolver());
    }
}
