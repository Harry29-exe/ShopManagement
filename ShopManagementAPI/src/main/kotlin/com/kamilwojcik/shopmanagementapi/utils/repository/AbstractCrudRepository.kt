package com.kamilwojcik.shopmanagementapi.utils.repository

import kotlinx.coroutines.flow.*
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.data.r2dbc.core.R2dbcEntityOperations
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query
import org.springframework.data.relational.core.query.Query.empty
import org.springframework.data.relational.core.query.Query.query

abstract class AbstractCrudRepository<T : Any, ID: Any>(
    protected val template: R2dbcEntityOperations,

    ) : AppCoroutineCrudRepository<T, ID> {

    protected abstract val table: EntityInfo<T>

    override suspend fun <S : T> save(entity: S): T {
        return template
            .insert(entity)
            .awaitSingle()
    }

    override fun <S : T> saveAll(entities: Iterable<S>): Flow<S> {
        return entities.asFlow().map {
            template.insert(it).awaitSingle()
        }
    }

    override fun <S : T> saveAll(entityStream: Flow<S>): Flow<S> {
        return entityStream.map {
            template.insert(it).awaitSingle()
        }
    }

    override suspend fun findById(id: ID): T? {
        return template.select(
            whereId(id)
            , table.clazz)
            .awaitFirstOrNull()
    }

    override suspend fun existsById(id: ID): Boolean {
        return template.exists(
            whereId(id)
            , table.clazz)
            .awaitSingle()
    }

    override fun findAll(): Flow<T> {
        return template.select(table.clazz)
            .all()
            .asFlow()
    }

    override fun findAllById(ids: Iterable<ID>): Flow<T> {
        return template.select(
            whereIdIn(ids.toCollection(ArrayList()))
            , table.clazz)
            .asFlow()
    }

    override fun findAllById(ids: Flow<ID>): Flow<T> = flow {
        val idsList: MutableList<ID> = ArrayList()
        ids.collect{ idsList.add(it) }

        emitAll(template
            .select(
                whereIdIn(idsList)
                , table.clazz)
            .asFlow()
        )
    }

    override suspend fun count(): Long {
        return template
            .count(empty(), table.clazz)
            .awaitSingle()
    }

    override suspend fun deleteById(id: ID): Int {
        return template
            .delete(whereId(id), table.clazz)
            .awaitSingle()
    }

    override suspend fun delete(entity: T): Int {
        val returnedEntity = template
            .delete(entity)
            .awaitSingleOrNull()
        
        return if(returnedEntity != null) 1 else 0
    }

    override suspend fun deleteAllById(ids: Iterable<ID>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(entities: Iterable<T>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun <S : T> deleteAll(entityStream: Flow<S>): Int {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll(): Int {
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
