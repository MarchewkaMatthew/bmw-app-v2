package com.bmwapp.flat.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlatConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}