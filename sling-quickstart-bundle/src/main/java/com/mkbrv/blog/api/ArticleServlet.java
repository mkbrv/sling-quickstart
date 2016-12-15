package com.mkbrv.blog.api;

import com.mkbrv.blog.model.Article;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.io.JSONWriter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Retrieves one Article
 * Created by mkbrv on 14/12/16.
 */
@SlingServlet(resourceTypes = "blog/pages/article", extensions = "xjson", methods = "GET")
public class ArticleServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        Article article = request.getResource().adaptTo(Article.class);
        JSONWriter jsonWriter = new JSONWriter(response.getWriter());
        new ArticleToJson(jsonWriter).toJson(article);
    }
}
