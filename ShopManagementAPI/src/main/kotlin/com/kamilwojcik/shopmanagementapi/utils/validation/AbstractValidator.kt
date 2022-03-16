package com.kamilwojcik.shopmanagementapi.utils.validation

abstract class AbstractValidator<out T : Any>(
    protected val value: T,
    val exToThrow: Exception = ValidationException(value)
)

inline fun <R : AbstractValidator<Any>> R.validateFunc(isValid: () -> Boolean): R {
    if(isValid()) {
        return this
    } else {
        throw this.exToThrow
    }
}
