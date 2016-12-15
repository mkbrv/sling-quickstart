package com.mkbrv.blog.service;

import com.mkbrv.blog.model.Article;
import org.apache.sling.api.resource.ResourceResolver;

import java.util.List;

/**
 * Created by mkbrv on 14/12/16.
 */
public interface ArticleService {

    /**
     * Retrieves latest articles;
     *
     * @return articles;
     */
    List<Article> findLatestArticles(final ResourceResolver resolver);
}
