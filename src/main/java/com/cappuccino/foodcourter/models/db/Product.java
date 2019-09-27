package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */

@Entity
@Table(name = "products")
public class Product extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "need_to_promote", nullable = false)
    private Boolean needToPromote;

    @Column(name = "description")
    private String description;

    public Integer getId() {
        return id;
    }

    public Product setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Product setPrice(Double price) {
        this.price = price;
        return this;
    }

    public Boolean getNeedToPromote() {
        return needToPromote;
    }

    public Product setNeedToPromote(Boolean needToPromote) {
        this.needToPromote = needToPromote;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }
}
