package com.kamilwojcik.shopmanagementapi.utils.validator

interface Validator<T> {

    /**
     * @returns if value is valid returns true otherwise returns false
     */
    fun isValid(value: T): Boolean

    /**
     * @returns if all values are valid returns true otherwise returns false
     */
    fun isValid(vararg values: T): Boolean

    /**
     * If any of given values are invalid throws Exception,
     * @throws Exception is produces by validator's exception factory
     */
    fun validate(value: T)

    /**
     * If given value is invalid throws Exception,
     * @throws Exception is produces by validator's exception factory
     */
    fun validate(vararg values: T)

    fun addValidationFunc(function: ValidationFunction<T>): Validator<T>

}
