package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "branches")
public class Branch extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "shopping_center_id")
    private ShoppingCenter shoppingCenter;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.DETACH
    )
    @JoinTable(
            joinColumns = @JoinColumn(
                    name = "branch_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            )
    )
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public Branch setProducts(Set<Product> products) {
        this.products = products;
        return this;
    }

    public ShoppingCenter getShoppingCenter() {
        return shoppingCenter;
    }

    public Branch setShoppingCenter(ShoppingCenter shoppingCenter) {
        this.shoppingCenter = shoppingCenter;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Branch setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Branch setName(String name) {
        this.name = name;
        return this;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public Branch setVendor(Vendor vendor) {
        this.vendor = vendor;
        return this;
    }
}
