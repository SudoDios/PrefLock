package me.sudodios.preflock

interface CipherAlgorithm {
    /*
    * baseKey for encrypt all inputs
    * */
    fun encrypt (baseKey : String,value : String) : String
    fun decrypt (baseKey: String,encrypted: String) : String

}