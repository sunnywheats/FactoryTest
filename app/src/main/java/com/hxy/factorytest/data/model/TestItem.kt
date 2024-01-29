package com.hxy.factorytest.data.model

data class TestItem(
    val name: String,
    val cls: String,
    val isSupport: Boolean,
    val state: Int,//fail=-1,nottest=0,pass=1
    val order: Int,//1..100
    val key: String
) {
    constructor(
        name: String,
        cls: String,
        isSupport: Boolean,
    ) : this(name, cls, isSupport, 0, 0,"key")
}