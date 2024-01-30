package com.hxy.factorytest.util


class SystemProperties {
    operator fun get(key: String): String? {
        var ret: String? = null
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            val mthd = clazz.getMethod(
                "get", *arrayOf<Class<*>>(
                    String::class.java
                )
            )
            mthd.isAccessible = true
            val obj = mthd.invoke(clazz, *arrayOf<Any>(key))
            if (obj != null && obj is String) {
                ret = obj
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    operator fun get(key: String?, def: String?): String? {
        var ret = def
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            val mthd = clazz.getMethod(
                "get", *arrayOf<Class<*>>(
                    String::class.java,
                    String::class.java
                )
            )
            mthd.isAccessible = true
            val obj = mthd.invoke(clazz, *arrayOf<Any?>(key, def))
            if (obj != null && obj is String) {
                ret = obj
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    fun getBoolean(key: String, def: Boolean): Boolean {
        var ret = def
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            val mthd = clazz.getMethod(
                "getBoolean", *arrayOf<Class<*>?>(
                    String::class.java,
                    Boolean::class.javaPrimitiveType
                )
            )
            mthd.isAccessible = true
            val obj = mthd.invoke(clazz, *arrayOf<Any>(key, def))
            if (obj != null && obj is Boolean) {
                ret = obj
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    fun getInt(key: String, def: Int): Int {
        var ret = def
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            val mthd = clazz.getMethod(
                "getInt", *arrayOf<Class<*>?>(
                    String::class.java,
                    Int::class.javaPrimitiveType
                )
            )
            mthd.isAccessible = true
            val obj = mthd.invoke(clazz, *arrayOf<Any>(key, def))
            if (obj != null && obj is Int) {
                ret = obj
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    fun getLong(key: String, def: Long): Long {
        var ret = def
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            val mthd = clazz.getMethod(
                "getLong", *arrayOf<Class<*>?>(
                    String::class.java,
                    Long::class.javaPrimitiveType
                )
            )
            mthd.isAccessible = true
            val obj = mthd.invoke(clazz, *arrayOf<Any>(key, def))
            if (obj != null && obj is Long) {
                ret = obj
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ret
    }

    operator fun set(key: String, value: String) {
        try {
            val clazz = Class.forName("android.os.SystemProperties")
            val mthd = clazz.getMethod(
                "set", *arrayOf<Class<*>>(
                    String::class.java,
                    String::class.java
                )
            )
            mthd.isAccessible = true
            mthd.invoke(clazz, *arrayOf<Any>(key, value))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}