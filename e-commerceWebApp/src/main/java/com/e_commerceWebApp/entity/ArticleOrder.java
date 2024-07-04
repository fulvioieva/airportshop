package com.e_commerceWebApp.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "article_order")
public class ArticleOrder {

    @EmbeddedId
    private ArticleOrderId id;

    @MapsId("articleId")
    @ManyToOne
    @JoinColumn(name = "article_id", referencedColumnName = "id", nullable = false)
    private Article article;

    @MapsId("orderId")
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Order order;

    @Column(name = "qty_ordered", nullable = false)
    private Integer qtyOrdered;

    public ArticleOrderId getId() {
        return id;
    }

    public void setId(ArticleOrderId id) {
        this.id = id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getQtyOrdered() {
        return qtyOrdered;
    }

    public void setQtyOrdered(Integer qtyOrdered) {
        this.qtyOrdered = qtyOrdered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleOrder that = (ArticleOrder) o;
        return Objects.equals(id, that.id) && Objects.equals(article, that.article) && Objects.equals(order, that.order) && Objects.equals(qtyOrdered, that.qtyOrdered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, article, order, qtyOrdered);
    }

    @Override
    public String toString() {
        return "ArticleOrder{" +
                "id=" + id +
                ", article=" + article +
                ", order=" + order +
                ", qtyOrdered=" + qtyOrdered +
                '}';
    }
}