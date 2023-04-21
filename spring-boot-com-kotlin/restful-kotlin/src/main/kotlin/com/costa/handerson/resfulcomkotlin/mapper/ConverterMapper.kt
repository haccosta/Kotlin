package com.costa.handerson.resfulcomkotlin.mapper

interface ConverterMapper<T, U> {

    fun mapper (t: T):U

}
