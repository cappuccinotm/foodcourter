package com.cappuccino.foodcourter.models.db;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "vendors")
public class Vendor extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "vendor")
    private Set<Branch> branches;
}
