package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "description")
    private String description;

    @Column(name = "time")
    private Date time;

    @Column(name = "price")
    private Double price;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Order setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getTime() {
        return time;
    }

    public Order setTime(Date time) {
        this.time = time;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Order setPrice(Double price) {
        this.price = price;
        return this;
    }
}
