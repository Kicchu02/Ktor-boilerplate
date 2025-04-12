package com.example

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import javax.sql.DataSource
import org.jooq.DSLContext
import org.jooq.Record
import org.jooq.TableField
import org.jooq.impl.DSL

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

    fun <T> transaction(isReadOnly: Boolean = false, block: (DSLContext) -> T): T {
        dataSource.connection.use { connection ->
            return try {
                connection.autoCommit = false
                connection.isReadOnly = isReadOnly
                val ctx = DSL.using(connection, org.jooq.SQLDialect.POSTGRES)
                val result = block(ctx)
                if (!isReadOnly) {
                    connection.commit()
                }
                result
            } catch (e: Exception) {
                if (!isReadOnly) {
                    connection.rollback()
                }
                throw e
            } finally {
                connection.isReadOnly = false
            }
        }
    }
}

fun <T : Any> Record.getNonNullValue(field: TableField<*, T?>): T {
    return this.get(field) ?: throw IllegalStateException("Field ${field.name} is unexpectedly null")
}
