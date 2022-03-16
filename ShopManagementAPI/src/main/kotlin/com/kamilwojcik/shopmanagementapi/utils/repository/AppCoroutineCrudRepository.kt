package com.kamilwojcik.shopmanagementapi.utils.repository

import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Mono

interface AppCoroutineCrudRepository<T, ID> {

    suspend fun <S : T> save(entity: S): T

    fun <S : T> saveAll(entities: Iterable<S>): Flow<S>

    fun <S : T> saveAll(entityStream: Flow<S>): Flow<S>


    suspend fun findById(id: ID): T?

    suspend fun existsById(id: ID): Boolean


    fun findAll(): Flow<T>

    fun findAllById(ids: Iterable<ID>): Flow<T>

    fun findAllById(ids: Flow<ID>): Flow<T>


    suspend fun count(): Long


    suspend fun deleteById(id: ID): Int

    suspend fun delete(entity: T): Int

    suspend fun deleteAllById(ids: Iterable<ID>): Int

    suspend fun deleteAll(entities: Iterable<T>): Int

    suspend fun <S : T> deleteAll(entityStream: Flow<S>): Int

    suspend fun deleteAll(): Int

}