#include <jni.h>
#include <string>
#include "encrypt.h"

using namespace std;

extern "C"
JNIEXPORT jstring JNICALL
Java_me_sudodios_preflock_VigenereCipher_encryptStr(JNIEnv *env, jobject thiz, jstring key,jstring value) {
    jboolean isCopy;
    const char *parsedKey = (env)->GetStringUTFChars(key, &isCopy);
    const char *parsedValue = (env)->GetStringUTFChars(value, &isCopy);
    string keyInput = parsedKey;
    string valueInput = parsedValue;

    string encrypted = encrypt(valueInput,keyInput);

    return env->NewStringUTF(encrypted.c_str());
}
extern "C"
JNIEXPORT jstring JNICALL
Java_me_sudodios_preflock_VigenereCipher_decryptStr(JNIEnv *env, jobject thiz, jstring key,jstring value) {
    jboolean isCopy;
    const char *parsedKey = (env)->GetStringUTFChars(key, &isCopy);
    const char *parsedValue = (env)->GetStringUTFChars(value, &isCopy);
    string keyInput = parsedKey;
    string valueInput = parsedValue;

    string encrypted = decrypt(valueInput,keyInput);

    return env->NewStringUTF(encrypted.c_str());
}