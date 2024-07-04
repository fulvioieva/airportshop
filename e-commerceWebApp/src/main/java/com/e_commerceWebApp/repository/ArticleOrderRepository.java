package com.e_commerceWebApp.repository;


import com.e_commerceWebApp.entity.ArticleOrder;
import com.e_commerceWebApp.entity.ArticleOrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleOrderRepository extends JpaRepository<ArticleOrder, ArticleOrderId> {
    @Query("SELECT ao FROM ArticleOrder ao WHERE ao.id.articleId = :articleId")
    List<ArticleOrder> findByArticleId(@Param("articleId") String articleId);

    @Query("SELECT ao FROM ArticleOrder ao WHERE ao.id.orderId = :orderId")
    List<ArticleOrder> findByOrderId(@Param("orderId") Integer orderId);

    boolean existsById (ArticleOrderId id);

}
