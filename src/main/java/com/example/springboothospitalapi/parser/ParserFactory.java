package com.example.springboothospitalapi.parser;

import com.example.springboothospitalapi.domain.Hospital;
import com.example.springboothospitalapi.parser.HospitalParser;
import com.example.springboothospitalapi.parser.Read;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserFactory {
    @Bean
    public Read<Hospital> hospitalRead(){
        return new Read<Hospital>(new HospitalParser(),true);
    }
}
