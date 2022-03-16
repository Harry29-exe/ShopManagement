package com.kamilwojcik.shopmanagementapi.utils.validator

class GenericExceptionFactory<T: Any> : ExceptionFactory<T, IllegalArgumentException> {

    override fun create(value: T): IllegalArgumentException {
        return IllegalArgumentException("Object of type: " + value::class.java +
                " with value: " + value.toString() +
                " is invalid")
    }

    override fun create(value: T, cause: Error): IllegalArgumentException {
        return IllegalArgumentException("Object of type: " + value::class.java +
                " with value: " + value.toString() +
                " is invalid because of " + cause.message, cause)
    }

    override fun createAndThrow(value: T) {
        throw create(value)
    }

    override fun createAndThrow(value: T, cause: Error) {
        throw create(value, cause)
    }
}