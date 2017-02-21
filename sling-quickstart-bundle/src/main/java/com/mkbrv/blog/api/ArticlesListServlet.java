package com.mkbrv.blog.api;

import com.mkbrv.blog.service.ArticleService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * List all articles;
 * Created by mkbrv on 14/12/16.
 */
@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/blog/articles",
        "sling.servlet.extensions=xjson",
        "sling.servlet.methods=GET"})
public class ArticlesListServlet extends SlingSafeMethodsServlet {

    @Reference
    private ArticleService articleService;

    protected void doGet(final SlingHttpServletRequest request,
                         final SlingHttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        JSONWriter jsonWriter = new JSONWriter(response.getWriter());
        try {
            jsonWriter.array();
            articleService.findLatestArticles(request.getResourceResolver()).forEach(article ->
                    new ArticleToJson(jsonWriter).toJson(article));
            jsonWriter.endArray();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
