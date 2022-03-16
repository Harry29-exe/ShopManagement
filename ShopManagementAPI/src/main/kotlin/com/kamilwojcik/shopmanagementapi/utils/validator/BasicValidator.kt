package com.kamilwojcik.shopmanagementapi.utils.validator

open class BasicValidator<T : Any> : Validator<T> {

    protected val validationFuncs: MutableList<ValidationFunction<T>> = ArrayList()

    var exceptionFactory: ExceptionFactory<T, Exception> =
        GenericExceptionFactory()


    override fun isValid(value: T): Boolean {
        for (func in validationFuncs) {
            if (!func.isValid(value)) {
                return false
            }
        }

        return true
    }

    override fun isValid(vararg values: T): Boolean {
        for(value in values) {
            if(!isValid(value)) {
                return false
            }
        }

        return true
    }

    override fun validate(value: T) {
        for (func in validationFuncs) {
            if (!func.isValid(value)) {
                exceptionFactory.createAndThrow(value)
            }
        }
    }

    override fun validate(vararg values: T) {
        for (value in values) {
            validate(value)
        }
    }

    override fun addValidationFunc(function: ValidationFunction<T>): BasicValidator<T> {
        validationFuncs.add(function)
        return this
    }

}