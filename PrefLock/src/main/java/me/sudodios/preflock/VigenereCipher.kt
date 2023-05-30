package me.sudodios.preflock

class VigenereCipher : CipherAlgorithm {

    init {
        System.loadLibrary("preflock")
    }

    private external fun encryptStr (key : String, value : String) : String
    private external fun decryptStr (key : String, value : String) : String

    override fun encrypt(baseKey: String,value: String): String {
        return encryptStr(baseKey,value)
    }

    override fun decrypt(baseKey: String,encrypted: String): String {
        return decryptStr(baseKey,encrypted)
    }

}