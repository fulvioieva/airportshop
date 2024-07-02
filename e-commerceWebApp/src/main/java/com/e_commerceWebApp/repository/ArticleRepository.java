package com.e_commerceWebApp.repository;

import com.e_commerceWebApp.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    List<Article> findByName(String name);
    Article findArticleByIdArticle(String idArticle);

}
