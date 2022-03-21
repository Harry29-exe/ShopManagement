package com.kamilwojcik.shopmanagementapi.utils.repository

import kotlinx.coroutines.flow.*
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.r2dbc.core.insert
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Query.empty
import org.springframework.data.relational.core.query.Query.query
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toFlux

abstract class AbstractCrudRepository<T : Any, ID: Any>(
    protected val template: R2dbcEntityOperations,

    ) : AppCoroutineCrudRepository<T, ID> {

    protected abstract val table: EntityInfo<T>

    override fun <S : T> save(entity: S): Mono<T> {
        return template.insert(entity)
    }

    override fun <S : T> saveAll(entities: Iterable<S>): Flux<S> = Flux.concat {
        entities.map {
            template.insert(it)
        }
    }

    override fun <S : T> saveAll(entityStream: Flux<S>): Flux<S> {
        return Flux
            .from(entityStream)
            .concatMap { template.insert(it) }
    }

    override fun findById(id: ID): Mono<T> {
        return template
            .selectOne(
                whereId(id),
                table.clazz
            )
    }

    override fun existsById(id: ID): Mono<Boolean> {
        return template.exists(
            whereId(id),
            table.clazz)
    }

    override fun findAll(): Flux<T> {
        return template.select(table.clazz).all()
    }

    override fun findAllById(ids: Iterable<ID>): Flux<T> {
        return template.select(
            whereIdIn(ids.toCollection(ArrayList()))
            , table.clazz)
    }

    override fun findAllById(ids: Flux<ID>): Flux<T> {
        return ids.collectList()
            .flatMapMany {
                template.select(
                    whereIdIn(it),
                    table.clazz
                )
            }
    }

    override fun count(): Mono<Long> {
        return template
            .count(empty(), table.clazz)
    }

    override fun deleteById(id: ID): Mono<Int> {
        return template
            .delete(whereId(id), table.clazz)
    }

    override fun delete(entity: T): Mono<Int> {
        val returnedEntity = template
            .delete(entity)
            .awaitSingleOrNull()
        
        return if(returnedEntity != null) 1 else 0
    }

    override fun deleteAllById(ids: Iterable<ID>): Mono<Int> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(entities: Iterable<T>): Mono<Int> {
        TODO("Not yet implemented")
    }

    override fun <S : T> deleteAll(entityStream: Flow<S>): Flux<Int> {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Mono<Int> {
        TODO("Not yet implemented")
    }


    protected fun whereId(id: ID): Query {
        return query(
            where(table.ID)
                .`is`(id)
        )
    }

    protected fun whereIdIn(ids: Collection<ID>): Query {
        return query(
            where(table.ID)
                .`in`(ids)
        )
    }

}
