package com.cappuccino.foodcourter.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Oybek Kasimov <MrKasimov> oibekkasymov@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public class ProductDTO{
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

    public ProductDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public ProductDTO setPrice(Double price) {
        this.price = price;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public Boolean getNeedToPromote() {
        return needToPromote;
    }

    public ProductDTO setNeedToPromote(Boolean needToPromote) {
        this.needToPromote = needToPromote;
        return this;
    }
}
