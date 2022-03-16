package com.kamilwojcik.shopmanagementapi.utils.validator

interface ExceptionFactory<T, out E : Exception> {

    fun create(value: T): E

    fun create(value: T, cause: Error): E

    fun createAndThrow(value: T)

    fun createAndThrow(value: T, cause: Error)

}