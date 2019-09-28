package com.cappuccino.foodcourter.models.api;

import com.cappuccino.foodcourter.models.db.FileAttachment;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yelshat Duskaliyev <semior> ura2178@gmail.com
 * @project foodcourter
 * @since 28.09.2019
 */
public class VendorDTO {
    @JsonProperty("name")
    private String name;

    @JsonProperty("logo")
    private FileAttachment logo;

    public VendorDTO(String name, FileAttachment logo) {
        this.name = name;
        this.logo = logo;
    }

    public FileAttachment getLogo() {
        return logo;
    }

    public VendorDTO setLogo(FileAttachment logo) {
        this.logo = logo;
        return this;
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
