package com.kunitskaya.module9;

import com.kunitskaya.module8.service.database.SqlQueryBuilder;
import com.kunitskaya.module9.highload.HighloadDatabaseOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

    @Bean
    public HighloadDatabaseOperations highloadDatabaceOperations() {
        return new HighloadDatabaseOperations();
    }

    @Bean
    public SqlQueryBuilder queryBuilder() {
        return new SqlQueryBuilder();
    }
}
