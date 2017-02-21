package com.mkbrv.blog.service;

import com.mkbrv.blog.model.Article;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javax.jcr.query.Query.JCR_SQL2;

/**
 * Created by mkbrv on 14/12/16.
 */
@Component(service = ArticleService.class)
public class DefaultArticleService implements ArticleService {

    @Override
    public List<Article> findLatestArticles(final ResourceResolver resolver) {
        List<Article> foundArticles = new ArrayList<>();
        Iterator<Resource> resourceArticles = resolver.findResources("SELECT * FROM [blog:article] AS a " +
                " ORDER BY a.[jcr:created] desc", JCR_SQL2);
        resourceArticles.forEachRemaining(resource -> foundArticles.add(resource.adaptTo(Article.class)));
        return foundArticles;
    }
}
