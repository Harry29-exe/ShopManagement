package com.kamilwojcik.shopmanagementapi.utils.repository

import kotlinx.coroutines.flow.Flow
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface AppCoroutineCrudRepository<T, ID> {

    fun <S : T> save(entity: S): Mono<T>

    fun <S : T> saveAll(entities: Iterable<S>): Flux<S>

    fun <S : T> saveAll(entityStream: Flux<S>): Flux<S>


    fun findById(id: ID): Mono<T>

    fun existsById(id: ID): Mono<Boolean>


    fun findAll(): Flux<T>

    fun findAllById(ids: Iterable<ID>): Flux<T>

    fun findAllById(ids: Flux<ID>): Flux<T>


    fun count(): Mono<Long>


    fun deleteById(id: ID): Mono<Int>

    fun delete(entity: T): Mono<Int>

    fun deleteAllById(ids: Iterable<ID>): Mono<Int>

    fun deleteAll(entities: Iterable<T>): Mono<Int>

    fun <S : T> deleteAll(entityStream: Flux<S>): Mono<Int>

    fun deleteAll(): Mono<Int>

}