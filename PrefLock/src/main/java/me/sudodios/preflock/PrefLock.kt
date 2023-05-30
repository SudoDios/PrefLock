package me.sudodios.preflock

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

object PrefLock {

    @SuppressLint("StaticFieldLeak")
    private lateinit var plc: PrefLockConf

    private const val LOG = "PrefLock -> "

    fun init (prefLockConf: PrefLockConf) {
        if (!this::plc.isInitialized) {
            plc = prefLockConf
        }
    }

    fun set (key : String,value: Any) {
        if (this::plc.isInitialized) {
            plc.sp.edit().putString(encryptKey(key), plc.cipher.encrypt(plc.baseKey,Gson().toJson(value))).apply()
        } else {
            Log.e(LOG,"PrefLock is not initialized")
        }
    }

    fun <T> get(key: String, type: Class<T>, def: T? = null): T? {
        if (this::plc.isInitialized) {
            val value = plc.sp.getString(encryptKey(key), def?.toString())
            val decodeValue = decodeValue(value)
            return try {
                Gson().fromJson(decodeValue, type)
            } catch (_ : JsonSyntaxException) {
                return null
            }
        } else {
            Log.e(LOG,"PrefLock is not initialized")
            return null
        }
    }

    fun remove (key: String) {
        if (this::plc.isInitialized) {
            plc.sp.edit().remove(encryptKey(key)).apply()
        } else {
            Log.e(LOG,"PrefLock is not initialized")
        }
    }

    fun contains (key: String) : Boolean {
        return if (this::plc.isInitialized) {
            plc.sp.contains(encryptKey(key))
        } else {
            Log.e(LOG,"PrefLock is not initialized")
            false
        }
    }

    //dec null
    private fun encryptKey (key: String) : String {
        return if (plc.encryptKeys) plc.cipher.encrypt(plc.baseKey,key) else key
    }

    private fun decodeValue (value: String?) : String? {
        return if (value == null) null else plc.cipher.decrypt(plc.baseKey,value)
    }

}