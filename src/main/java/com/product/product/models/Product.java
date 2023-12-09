package com.product.product.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private String image;
    @ManyToOne(optional = false)
    private Category category;

    @OneToOne(cascade = jakarta.persistence.CascadeType.REMOVE, optional = false)
    @JoinColumn(nullable = false)
    private Price price;
}
