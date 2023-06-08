<img src="https://raw.githubusercontent.com/SudoDios/PrefLock/master/icon.png" alt="drawing" width="160"/>

# PrefLock
A shared preferences allow encryption (AES & Vigenere Ciphers) and any type data (handle null & invalid types)

[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![](https://jitpack.io/v/SudoDios/PrefLock.svg)](https://jitpack.io/#SudoDios/PrefLock)

### Add to your project
Step 1 : Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
Step 2 : Add the dependency:
```gradle
dependencies {
	 implementation 'com.github.SudoDios:PrefLock:1.3'
}
```
### How to use
#### configuration & init
```kotlin
//config your PrefLock
val prefLockConf = PrefLockConf(
      context = this,
      baseKey = "yourAnyKeyHere",
      /*not required*/ encryptKeys = true,
      /*not required*/ name = "custom shared preferences name",
      /*not required (default is AES)*/ cipher = VigenereCipher()
)

//init PrefLock at startup like on Application class
PrefLock.init(prefLockConf)
//now is ready to use
```
#### save values
```kotlin
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
PrefLock.set("key", /*your data*/ AnyDataHere)
```
#### get values
```kotlin
//get saved keys by generic types
//default values not required to pass (is null)
PrefLock.get("key", /*type of get*/ String::class.java, /*default value*/"")
PrefLock.get("key", /*type of get*/ Float::class.java, /*default value*/0f)
PrefLock.get("key", /*type of get*/ Double::class.java, /*default value*/0.0)
PrefLock.get("key", /*type of get*/ Long::class.java, /*default value*/0L)
PrefLock.get("key", /*type of get*/ Boolean::class.java, /*default value*/false)
PrefLock.get("key", /*type of get*/ ArrayList<String>().javaClass, /*default value*/ arrayListOf())
PrefLock.get("key", /*type of get*/ HashMap<String,String>().javaClass, /*default value*/ mutableMapOf())
PrefLock.get("key", /*type of get*/ User::class.java, /*default value*/ User())
PrefLock.get("key", /*your data type*/, /*default of data type*/)
```
#### remove a key
```kotlin
//remove key
PrefLock.remove("key")
```
#### contain a key
```kotlin
//check contains key
PrefLock.contains("key")
```
### Advanced
#### make your custom cipher
```kotlin
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
```
### Comming soon
- Support live data
