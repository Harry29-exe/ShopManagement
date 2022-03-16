package com.kamilwojcik.shopmanagementapi.utils.validation

class ValidationException(
    val value: Any,
    val reason: String? = null,
    val thrownAt: Class<Any>? = null,
    val prefix: String? = null
)

    : Exception(message = "Object of type: ${value::class.java} " +
        " with value: $value" +
        " is invalid${reason ?:"."}") {



}