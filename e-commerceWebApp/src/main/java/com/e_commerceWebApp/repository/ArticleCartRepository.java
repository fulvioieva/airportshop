package com.e_commerceWebApp.repository;

import com.e_commerceWebApp.entity.ArticleCart;
import com.e_commerceWebApp.entity.ArticleCartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCartRepository extends JpaRepository<ArticleCart, ArticleCartId> {

}