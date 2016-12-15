package com.mkbrv.blog.api;

import com.mkbrv.blog.service.ArticleService;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by mkbrv on 14/12/16.
 */
@SlingServlet(paths = "/blog/articles", extensions = "xjson", methods = "GET")
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
