package com.e_commerceWebApp.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ArticleOrderId implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @Column(name = "article_id")
    private String articleId;

    @Column(name = "order_id")
    private Integer orderId;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleOrderId that = (ArticleOrderId) o;
        return Objects.equals(articleId, that.articleId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, orderId);
    }

    @Override
    public String toString() {
        return "ArticleOrderId{" +
                "articleId='" + articleId + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}