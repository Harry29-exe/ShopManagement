package com.wojcikk.shopmanagementapi.utils.validation.validators

import com.wojcikk.shopmanagementapi.exception.validation.ValidationException

inline fun <T : Comparable<T>> T.max(maxVal: T): T {
    if (this > maxVal) {
        throw ValidationException(this, "Value: $this is bigger that $maxVal")
    }

    return this;
}

inline fun <T : Comparable<T>> T.min(minVal: T): T {
    if (this < minVal) {
        throw ValidationException(this, "Value $this is smaller that $minVal")
    }

    return this;
}

inline fun <T:Comparable<T>> T.between(minVal: T, maxVal: T): T {
    if (this >= maxVal) {
        throw ValidationException(this, "Value: $this is bigger/equal to $maxVal")
    }
    if (this <= minVal) {
        throw ValidationException(this, "Value $this is smaller/equal to $minVal")
    }

    return this
}