package me.sudodios.preflockapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.sudodios.preflock.CipherAlgorithm
import me.sudodios.preflock.PrefLock
import me.sudodios.preflock.PrefLockConf
import me.sudodios.preflock.VigenereCipher

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //config your PrefLock
        val prefLockConf = PrefLockConf(
            context = this,
            baseKey = "yourAnyKeyHere",
            encryptKeys = true,
            name = "custom shared preferences name",
            cipher = VigenereCipher()
        )

        //init PrefLock at startup like on Application class
        PrefLock.init(prefLockConf)


        //save any type of data
        PrefLock.set("key", /*string*/ "Sudodios")
        PrefLock.set("key", /*int*/ 20)
        PrefLock.set("key", /*float*/ 20f)
        PrefLock.set("key", /*double*/ 20.0)
        PrefLock.set("key", /*long*/ 20L)
        PrefLock.set("key", /*boolean*/ true)
        PrefLock.set("key", /*arraylists any type*/ ArrayList<String>())
        PrefLock.set("key", /*maps any type*/ mutableMapOf<String,String>())
        PrefLock.set("key", /*custom objects ex.( data classes)*/ User())


        //get saved keys by generic types
        PrefLock.get("key", /*type of get*/ String::class.java, /*default value*/"")
        PrefLock.get("key", /*type of get*/ Float::class.java, /*default value*/0f)
        PrefLock.get("key", /*type of get*/ Double::class.java, /*default value*/0.0)
        PrefLock.get("key", /*type of get*/ Long::class.java, /*default value*/0L)
        PrefLock.get("key", /*type of get*/ Boolean::class.java, /*default value*/false)
        PrefLock.get("key", /*type of get*/ ArrayList<String>().javaClass, /*default value*/ arrayListOf())
        PrefLock.get("key", /*type of get*/ HashMap<String,String>().javaClass, /*default value*/ mutableMapOf())
        PrefLock.get("key", /*type of get*/ User::class.java, /*default value*/ User())


    }

    //make your custom cipher
    class CustomCipher : CipherAlgorithm {

        /*
        * pass this class to PrefLockConf
        * */

        init {
            //initiate your cipher
        }

        override fun encrypt(baseKey: String, value: String): String {
            return "your cipher encrypted"
        }
        override fun decrypt(baseKey: String, encrypted: String): String {
            return "your cipher decrypted"
        }

    }


    data class User(
        var name : String = "",
        var age : Int = 0,
        var avg : Double = 18.9,
    )

}
