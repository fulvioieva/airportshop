package com.e_commerceWebApp.repository;

import com.e_commerceWebApp.entity.ArticleCart;
import com.e_commerceWebApp.entity.ArticleCartId;
import com.e_commerceWebApp.entity.ArticleOrder;
import com.e_commerceWebApp.entity.ArticleOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleCartRepository extends JpaRepository<ArticleCart, ArticleCartId> {

    @Query("SELECT ac FROM ArticleCart ac WHERE ac.id.articleId = :articleId")
    List<ArticleCart> findByArticleId(@Param("articleId") String articleId);

    @Query("SELECT ac FROM ArticleCart ac WHERE ac.id.cartId = :cartId")
    List<ArticleCart> findByCartId(@Param("cartId") Integer cartId);

    boolean existsById (ArticleCartId id);

}