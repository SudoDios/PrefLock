package me.sudodios.preflock

import android.util.Base64
import java.security.Key
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class AESCipher (var bit: Int = 128, iv: String? = null) : CipherAlgorithm {

    private var key: Key? = null
    private var iv: IvParameterSpec? = null
    private var cipher: Cipher? = null

    init {
        if (iv != null) {
            this.iv = IvParameterSpec(getHash("MD5", iv))
        } else {
            this.iv = DEFAULT_IV
        }
        init()
    }

    private fun init() {
        cipher = try {
            Cipher.getInstance(TRANSFORMATION)
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

    private fun encryptStr(baseKey : String,str: String): String {
        return try {
            if (bit == 256) {
                this.key = SecretKeySpec(getHash("SHA-256", baseKey), ALGORITHM)
            } else {
                this.key = SecretKeySpec(getHash("MD5", baseKey), ALGORITHM)
            }
            encrypt(str.toByteArray(charset("UTF-8")))
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

    private fun encrypt(data: ByteArray?): String {
        return try {
            cipher!!.init(Cipher.ENCRYPT_MODE, key, iv)
            val encryptData = cipher!!.doFinal(data)
            String(Base64.encode(encryptData, Base64.DEFAULT), charset("UTF-8"))
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

    private fun decryptStr(baseKey : String,str: String?): String {
        return try {
            if (bit == 256) {
                this.key = SecretKeySpec(getHash("SHA-256", baseKey), ALGORITHM)
            } else {
                this.key = SecretKeySpec(getHash("MD5", baseKey), ALGORITHM)
            }
            decrypt(Base64.decode(str, Base64.DEFAULT))
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

    private fun decrypt(data: ByteArray?): String {
        return try {
            cipher!!.init(Cipher.DECRYPT_MODE, key, iv)
            val decryptData = cipher!!.doFinal(data)
            String(decryptData, charset("UTF-8"))
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        }
    }

    companion object {

        private val DEFAULT_IV = IvParameterSpec(byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0))

        private const val ALGORITHM = "AES"

        private const val TRANSFORMATION = "AES/CBC/PKCS5Padding"

        private fun getHash(algorithm: String, text: String): ByteArray {
            return try {
                getHash(algorithm, text.toByteArray(charset("UTF-8")))
            } catch (ex: Exception) {
                throw RuntimeException(ex.message)
            }
        }

        private fun getHash(algorithm: String, data: ByteArray): ByteArray {
            return try {
                val digest = MessageDigest.getInstance(algorithm)
                digest.update(data)
                digest.digest()
            } catch (ex: Exception) {
                throw RuntimeException(ex.message)
            }
        }
    }


    override fun encrypt(baseKey : String,value: String) : String {
        return encryptStr(baseKey,value)
    }

    override fun decrypt(baseKey: String, encrypted: String) : String {
        return decryptStr(baseKey,encrypted)
    }

}