package me.jeonghui.springbootlevel2.dto;

import lombok.Getter;
import me.jeonghui.springbootlevel2.entity.Article;

@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }

}
