package com.wojcikk.shopmanagementapi.utils

typealias SimpleFun<V> = () -> V

interface Wrapper {

    fun <V> wrap(f: SimpleFun<V>): SimpleFun<V>

}

inline fun <V> wrap(wrapper: Wrapper, noinline func: SimpleFun<V>): V {
    return wrapper.wrap(func)()
}

operator fun Wrapper.plus(other: Wrapper): Wrapper {
    val first = this
    return object : Wrapper {
        override fun <V> wrap(f: SimpleFun<V>): SimpleFun<V> {
            return first.wrap(other.wrap(f))
        }
    }
}

//fun hasRole(vararg roles: String): WrapFunc
//        = object : WrapFunc { override fun <V> wrap(f: () -> V): () -> V = {
//
//    if (roles.size > 2) {
//        throw IllegalArgumentException()
//    }
//    f()
//
//} }
