package com.home.tblmgmt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${app.schema.name}")
    private String schemaName;

    public String getSchemaName() {
        return schemaName;
    }
}
