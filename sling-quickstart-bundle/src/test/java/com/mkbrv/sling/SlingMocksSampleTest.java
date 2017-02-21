package com.mkbrv.sling;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.junit.SlingContext;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mkbrv on 11/12/16.
 */
public class SlingMocksSampleTest {


    @Rule
    public SlingContext context = new SlingContext(ResourceResolverType.JCR_OAK);

    @Test
    public void contextIsLoaded() {
        context.load().json("/content.json", "/content");

        final ResourceResolver resolver = context.resourceResolver();
        Resource resource = resolver.getResource("/content");

        assertNotNull(resource);
        assertNotNull(resource.getChild("articles"));
    }


}
