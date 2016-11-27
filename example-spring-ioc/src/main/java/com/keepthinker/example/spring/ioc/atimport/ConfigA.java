package com.keepthinker.example.spring.ioc.atimport;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigA {
public @Bean A a() { return new A(); }
}