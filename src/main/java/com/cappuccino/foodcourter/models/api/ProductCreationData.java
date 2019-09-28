package com.cappuccino.foodcourter.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public class ProductCreationData {
    @JsonProperty("name")
    private String name;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("description")
    private String description;

    @JsonProperty("needToPromote")
    private Boolean needToPromote = false;

    public String getName() {
        return name;
    }

    public boolean isValid(){
        return !(name.equals("") || price <= 0.0);
    }

    public ProductCreationData setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductCreationData setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductCreationData setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getNeedToPromote() {
        return needToPromote;
    }

    public ProductCreationData setNeedToPromote(Boolean needToPromote) {
        this.needToPromote = needToPromote;
        return this;
    }
}
