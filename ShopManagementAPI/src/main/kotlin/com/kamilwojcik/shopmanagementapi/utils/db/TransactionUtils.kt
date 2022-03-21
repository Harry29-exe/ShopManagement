package com.kamilwojcik.shopmanagementapi.utils.db

import com.kamilwojcik.shopmanagementapi.config.db.DBTransactionManager.transactionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitLast
import kotlinx.coroutines.reactive.publish
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.runBlocking
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.ReactiveTransaction
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.reactive.TransactionalOperator
import org.springframework.transaction.reactive.executeAndAwait
import org.springframework.transaction.reactive.transactional
import org.springframework.transaction.support.DefaultTransactionDefinition
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import java.util.*

fun txDetails(
    propagationBehavior: Int? = null,
    isolationLevel: Int? = null,
    timeout: Int? = null,
    readOnly: Boolean? = null,
    name: String? = null,

): TransactionDefinition {
    val txDef = DefaultTransactionDefinition()
    if (propagationBehavior != null)
        txDef.propagationBehavior = propagationBehavior
    if (isolationLevel != null)
        txDef.isolationLevel = isolationLevel
    if (timeout != null)
        txDef.timeout = timeout
    if (readOnly != null)
        txDef.isReadOnly = readOnly
    if (name != null)
        txDef.setName(name)

    return txDef
}

suspend fun <V:Any> TransactionDefinition.inTx( func: suspend () -> V?): V? {
    return TransactionalOperator
        .create(transactionManager, this)
        .executeAndAwait { func () }
}

fun <V: Any> TransactionDefinition.inTx(func: () -> Flow<V>): Flow<V> {
    return func()
        .transactional(
            TransactionalOperator.create(
                transactionManager,
                this)
        )
}

suspend fun <V: Any> inTx( func: suspend () -> V?): V? {
    return TransactionalOperator.create(transactionManager).executeAndAwait {
        func()
    }
}

fun <V: Any> Flow<V>.inTx(): Flow<V> {
    return this.transactional(TransactionalOperator.create(transactionManager))
}
