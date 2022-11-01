package com.example.springboothospitalapi.parser;

import com.example.springboothospitalapi.domain.Hospital;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReadFactory {
    @Bean
    public Read<Hospital> hospitalRead(){
        return new Read<Hospital>(new HospitalParser(),true);
    }
}
