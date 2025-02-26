package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.sql.DataSource

object DatabaseFactory {
    private lateinit var dataSource: DataSource

    fun init() {
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:postgresql://localhost:5432/demo_db"
            username = "admin"
            password = "Demo@123"
            driverClassName = "org.postgresql.Driver"
            maximumPoolSize = 10
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        }
        dataSource = HikariDataSource(config)
    }

    fun createDSLContext() = DSL.using(dataSource, SQLDialect.POSTGRES)
}
