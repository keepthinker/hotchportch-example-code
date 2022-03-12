package com.keepthinker.example.spring.ioc;

public class GeneralConfig {
    private String databaseUrl;

    public String getDatabaseUrl() {
        return databaseUrl;
    }

    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }
}
