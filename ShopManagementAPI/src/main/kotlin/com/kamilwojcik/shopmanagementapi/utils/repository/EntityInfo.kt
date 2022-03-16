package com.kamilwojcik.shopmanagementapi.utils.repository

interface EntityInfo<T> {

    val clazz: Class<T>

    val ID: String

}