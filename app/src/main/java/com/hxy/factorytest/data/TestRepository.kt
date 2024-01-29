package com.hxy.factorytest.data

import com.hxy.factorytest.data.model.TestItem

class TestRepository(private val localDataSource: LocalDataSource) {
    fun getAllTest(): List<TestItem> {
        return localDataSource.getAllTest()
    }

    fun getMainTest(): List<TestItem> {
        return localDataSource.getMainTest()
    }
}