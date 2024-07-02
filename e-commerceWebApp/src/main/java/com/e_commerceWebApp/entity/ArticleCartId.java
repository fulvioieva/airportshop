package com.e_commerceWebApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleCartId implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "articleId")
    private String articleId;

    @Column(name = "cartId")
    private Integer cartId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleCartId that = (ArticleCartId) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(cartId, that.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, cartId);
    }

    @Override
    public String toString() {
        return "ArticleCartId{" +
                "articleId='" + articleId + '\'' +
                ", cartId=" + cartId +
                '}';
    }
}
