package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;

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
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

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
