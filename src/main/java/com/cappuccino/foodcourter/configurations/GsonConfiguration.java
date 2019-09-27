package com.cappuccino.foodcourter.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class GsonConfiguration {

    @Bean(name = "gsonDateFormatter")
    @Qualifier("gsonDateFormatter")
    public Gson provideGsonInstance(){
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .disableHtmlEscaping()
                .create();
    }

}
