package com.wojcikk.shopmanagementapi.utils.validation

import com.wojcikk.shopmanagementapi.exception.validation.ValidationException
import com.wojcikk.shopmanagementapi.utils.SimpleFun
import com.wojcikk.shopmanagementapi.utils.Wrapper

inline fun <T> validate(vararg objects: Validated, func: () -> T): T {
    for (obj in objects) {
        if (!obj.isValid()) {
            throw ValidationException()
        }
    }

    return func()
}

fun validate(vararg objects: Validated): Wrapper
= object : Wrapper { override fun <V> wrap(f: SimpleFun<V>): SimpleFun<V>
{
    for (obj in objects) {
        if (!obj.isValid()) {
            //todo change this exception
            throw ValidationException()
        }
    }

    return f
} }