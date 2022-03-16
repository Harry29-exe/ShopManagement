package com.kamilwojcik.shopmanagementapi.utils.validator


fun interface ValidationFunction<T> {

    /**
     * @return returns true is value is valid otherwise returns false
     */
    fun isValid(t: T): Boolean

}