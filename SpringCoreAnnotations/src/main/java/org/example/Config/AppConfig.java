package org.example.Config;

import org.example.Model.Engine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
public class AppConfig {

    @Bean
    @Qualifier("v8Engine")
    public Engine v8Engine() {
        return new Engine("V8", 400);
    }

    @Bean
    @Qualifier("electricEngine")
    public Engine electricEngine() {
        return new Engine("Electric", 200);
    }
}
