package com.vasilevkin.fragmentsroom.repository.datasource


class CacheIsNotValidException(cause: Throwable? = null) : RuntimeException(cause) {
    override fun toString(): String {
        return "CacheIsNotValidException: error=\"Cache is not valid.\""
    }
}