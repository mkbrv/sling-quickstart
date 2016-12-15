package com.mkbrv.blog.api;

import com.mkbrv.blog.model.Article;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.io.JSONWriter;

/**
 * Created by mkbrv on 14/12/16.
 */
public class ArticleToJson {

    private final JSONWriter writer;

    public ArticleToJson(JSONWriter writer) {
        this.writer = writer;
    }


    public void toJson(final Article article) {
        try {
            writer.object()
                    .key("path").value(article.getPath())
                    .key("title").value(article.getTitle())
                    .key("teaser").value(article.getTeaser())
                    .key("text").value(article.getText())
                    .key("author").value(article.getAuthor())
                    .key("image").value(article.getImage())
                    .key("date").value(article.getDate())
                    .endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
