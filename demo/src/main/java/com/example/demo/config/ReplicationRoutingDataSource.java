package com.example.demo.config;

import com.example.demo.context.DbContextHolder;
import com.example.demo.enums.DbType;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

import java.util.Objects;

public class ReplicationRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return Objects.nonNull(DbContextHolder.get()) ? DbContextHolder.get() : DbType.MASTER;
    }

}
