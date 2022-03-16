package com.kamilwojcik.shopmanagementapi.utils.validator.extensions

import com.kamilwojcik.shopmanagementapi.utils.validator.BasicValidator
import com.kamilwojcik.shopmanagementapi.utils.validator.Validator

fun stringValidator(): Validator<String> {
    return BasicValidator()
}

fun Validator<String>.charOrDigit(): Validator<String> = addValidationFunc { v: String ->
    v.toCharArray()
        .none { c-> !c.isLetterOrDigit() }
}

inline fun charOrDigit(): Validator<String> =
    stringValidator().charOrDigit()



fun Validator<String>.asciiOnly(): Validator<String> =
    addValidationFunc { v: String ->
        v.chars()
            .anyMatch{ c -> c < 256 }
    }

inline fun asciiOnly(): Validator<String> =
    stringValidator().asciiOnly()



fun Validator<String>.asciiCharOrDigit(): Validator<String> = addValidationFunc { v: String ->
    v.toCharArray()
        .none { !it.isLetterOrDigit() || it.code > 255 }
}

inline fun asciiCharOrDigit(): Validator<String> =
    stringValidator().asciiCharOrDigit()




