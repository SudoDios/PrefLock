package me.sudodios.preflock

import android.app.Activity
import android.content.Context

data class PrefLockConf(
    var context: Context,
    var baseKey : String,
    var encryptKeys : Boolean = true,
    var name : String = "prefLock",
    var cipher: CipherAlgorithm = AESCipher(256)
) {

    val sp = context.getSharedPreferences(name, Activity.MODE_PRIVATE)!!

}