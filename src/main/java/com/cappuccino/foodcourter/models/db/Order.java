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


}
