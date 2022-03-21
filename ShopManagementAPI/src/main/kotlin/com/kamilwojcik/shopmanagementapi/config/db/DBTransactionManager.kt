package com.kamilwojcik.shopmanagementapi.config.db

import org.springframework.r2dbc.connection.R2dbcTransactionManager

object DBTransactionManager {

    val transactionManager: R2dbcTransactionManager =
        R2dbcTransactionManager(DBConnectionConfig.connectionFactory)



}