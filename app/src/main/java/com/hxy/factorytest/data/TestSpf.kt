package com.hxy.factorytest.data

import android.content.Context
import android.content.SharedPreferences
import com.hxy.factorytest.util.Const

class TestSpf (private val context: Context){
    private lateinit var spf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    init {
        spf = context.getSharedPreferences("factory_test",Context.MODE_PRIVATE)
        editor = spf.edit()
    }

    fun getState(key:String): Int{
        return spf.getInt(key, Const.NOT_TEST)
    }

    fun setState(key:String,state:Int){
        editor.putInt(key,state).commit()
    }
}