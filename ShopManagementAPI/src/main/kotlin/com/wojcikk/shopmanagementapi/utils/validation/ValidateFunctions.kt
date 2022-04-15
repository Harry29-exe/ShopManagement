package com.wojcikk.shopmanagementapi.utils.validation

import com.wojcikk.shopmanagementapi.exception.validation.ValidationException

inline fun <T> validate(vararg objects: Validated, func: () -> T): T {
    for (obj in objects) {
        if (!obj.isValid()) {
            throw ValidationException()
        }
    }

    return func()
}

