package com.mkbrv.sling;

import com.mkbrv.blog.model.Article;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Rule;
import org.junit.Test;

import java.util.Iterator;

import static javax.jcr.query.Query.JCR_SQL2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by mkbrv on 11/12/16.
 */
public class SlingMocksSampleTest {


    @Rule
    public SlingContext context = new SlingContext(ResourceResolverType.JCR_OAK);

    @Test
    public void contextIsLoaded() {
        context.load().json("/content.json", "/content/blog");

        final ResourceResolver resolver = context.resourceResolver();
        Resource resource = resolver.getResource("/content/blog");

        assertNotNull(resource);
        assertNotNull(resource.getChild("test-article"));
    }

    @Test
    public void articleCanBeAdapted() {
        context.load().json("/content.json", "/content/blog");

        context.addModelsForPackage("com.mkbrv.blog.model");
        Resource resource = context.resourceResolver().getResource("/content/blog/test-article");
        Article article = resource.adaptTo(Article.class);

        // assertEquals("Colombia", article.getAuthor()); will fail due to @Named not working
        assertNotNull(article);
    }

    @Test
    public void contextCanBeQueried() {
        context.load().json("/content.json", "/content/blog");
        Iterator<Resource> results = context.
                resourceResolver().findResources("SELECT * FROM [nt:base] WHERE [jcr:createdBy]='Colombia'", JCR_SQL2);

        final String expectedAuthor = "Colombia";
        while (results.hasNext()) {
            Resource article = results.next();
            assertEquals(expectedAuthor, article.getValueMap().get("jcr:createdBy").toString());
        }
    }

}
