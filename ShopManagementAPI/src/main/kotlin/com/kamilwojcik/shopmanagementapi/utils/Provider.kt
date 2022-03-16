package com.kamilwojcik.shopmanagementapi.utils

interface Provider<T> {

    fun provide(): T

}