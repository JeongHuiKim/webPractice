package me.jeonghui.springbootlevel2.controller;

import lombok.RequiredArgsConstructor;
import me.jeonghui.springbootlevel2.dto.AddArticleRequest;
import me.jeonghui.springbootlevel2.dto.ArticleResponse;
import me.jeonghui.springbootlevel2.dto.UpdateArticleRequest;
import me.jeonghui.springbootlevel2.entity.Article;
import me.jeonghui.springbootlevel2.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class BlogApiController {

    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request, Principal principal) {
        Article saveArticle = blogService.save(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(saveArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.status(HttpStatus.OK)
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {

        Article article = blogService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(new ArticleResponse(article));
    }

    @DeleteMapping("api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {

        blogService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {

        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(updatedArticle);
    }


}
