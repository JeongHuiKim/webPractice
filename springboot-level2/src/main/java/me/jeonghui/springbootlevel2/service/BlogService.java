package me.jeonghui.springbootlevel2.service;

import com.nimbusds.jose.proc.SecurityContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.jeonghui.springbootlevel2.dto.AddArticleRequest;
import me.jeonghui.springbootlevel2.dto.UpdateArticleRequest;
import me.jeonghui.springbootlevel2.entity.Article;
import me.jeonghui.springbootlevel2.repository.BlogRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request, String userName) {
        return blogRepository.save(request.toEntity(userName));
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {

        Article article = blogRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("not fount : " + id));

        authorizeArticleAuthor(article);
        blogRepository.delete(article);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {

        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        authorizeArticleAuthor(article);
        article.update(request.getTitle(), request.getContent());

        return article;
    }

    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
