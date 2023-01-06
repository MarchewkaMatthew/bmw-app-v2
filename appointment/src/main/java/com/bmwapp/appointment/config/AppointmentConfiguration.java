package com.bmwapp.appointment.config;

import com.bmwapp.appointment.dto.AppointmentDto;
import com.bmwapp.appointment.model.Appointment;
import org.modelmapper.*;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class AppointmentConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());

        modelMapper.addMappings(new PropertyMap<Appointment, AppointmentDto>() {
            @Override
            protected void configure() {
                using(toStringDateTime).map(source.getAppointmentDate(), destination.getAppointmentDate());
            }
        });

        modelMapper.addMappings(new PropertyMap<AppointmentDto, Appointment>() {
            @Override
            protected void configure() {
                using(toLocalDateTime).map(source.getAppointmentDate(), destination.getAppointmentDate());
            }
        });

        return modelMapper;
    }

    Converter<String, LocalDateTime> toLocalDateTime = new AbstractConverter<String, LocalDateTime>() {
        @Override
        protected LocalDateTime convert(String source) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime localDate = LocalDateTime.parse(source, format);
            return localDate;
        }
    };

    Converter<LocalDateTime, String> toStringDateTime = new AbstractConverter<LocalDateTime, String>() {
        @Override
        protected String convert(LocalDateTime source) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            String localDateString = source.format(format);
            return localDateString;
        }
    };

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
