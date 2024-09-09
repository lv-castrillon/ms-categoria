package com.pragma.categoria.application.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.pragma.categoria.domain.impl")
public class CategoriaConfig {
}
