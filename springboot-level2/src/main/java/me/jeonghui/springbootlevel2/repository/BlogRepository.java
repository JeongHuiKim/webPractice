package me.jeonghui.springbootlevel2.repository;

import me.jeonghui.springbootlevel2.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}
