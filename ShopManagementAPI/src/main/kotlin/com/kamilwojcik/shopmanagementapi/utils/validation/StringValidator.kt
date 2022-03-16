package com.kamilwojcik.shopmanagementapi.utils.validation

class StringValidator : AbstractValidator<String> {

    constructor(value: String, exceptionToThrow: Exception)
            : super(value, exceptionToThrow)

    constructor(value: String)
            : super(value)

    fun charOrDigit(): StringValidator = validateFunc {
        value.toCharArray()
            .none { !it.isLetterOrDigit() }
    }


    fun charsOnly(): StringValidator = validateFunc {
        value.toCharArray()
            .none { !it.isLetter() }
    }

    fun digitsOnly(): StringValidator = validateFunc {
        value.toCharArray().none{ !it.isDigit() }
    }

    fun ascii(): StringValidator = validateFunc {
        value.chars().noneMatch{ it > 255 }
    }

}

fun String.validate(): StringValidator =
    StringValidator(this)

fun String.validate(exceptionToThrow: Exception): StringValidator =
    StringValidator(this, exceptionToThrow)