package com.cappuccino.foodcourter.models.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public class VendorDTO {
    @JsonProperty("name")
    private String name;

    public VendorDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public VendorDTO setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isValid() {
        return !name.isEmpty();
    }
}
