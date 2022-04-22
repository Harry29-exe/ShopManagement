package com.wojcikk.shopmanagementapi.utils.validation

import com.wojcikk.shopmanagementapi.exception.validation.ValidationException
import com.wojcikk.shopmanagementapi.utils.SimpleFun
import com.wojcikk.shopmanagementapi.utils.Wrapper

fun validate(vararg objects: Validated): Wrapper
= object : Wrapper { override fun <V> wrap(f: SimpleFun<V>): SimpleFun<V>
{
    for (obj in objects) {
        if (!obj.isValid()) {
            //todo change this exception
            throw ValidationException(obj, "Object validation function returned false")
        }
    }

    return f
} }