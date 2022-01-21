package cn.lukas.utils.core.module.encryption;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * https://github.com/ms-android/android-module-encryption
 * Android8.0以上才可以使用
 *
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class AESUtils {

    private static KeyGenerator keyGen;
    private static Cipher cipher;
    private static final String algorithmStr = "AES/ECB/PKCS5Padding";
    private static String keyStr = "aaaaaa";

    static {
        try {
            // 初始化keyGen
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String string = "{\"name\": \"test\"}";
        String string2 = "www";
        String encryptString = encrypt(string);
        System.out.println("原始数据: " + string + " 加密后: " + encryptString + " 解密后: " + decrypt(encryptString));
        String encryptString2 = encrypt(string2);
        System.out.println("原始数据: " + string2 + " 加密后: " + encryptString2 + " 解密后: " + decrypt(encryptString2));
    }

    /**
     * 加密方法.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String encrypt(String content) {
        byte[] encryptedBytes = null;
        Key key = new SecretKeySpec(keyStr.getBytes(), "AES");
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            encryptedBytes = cipher.doFinal(stringToBytes(content));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return bytesToString(Base64.getEncoder().encode(encryptedBytes));
    }

    /**
     * 解密方法.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static String decrypt(String content) {
        byte[] originBytes = null;

        Key key = new SecretKeySpec(keyStr.getBytes(), "AES");
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            originBytes = cipher.doFinal(Base64.getDecoder().decode(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bytesToString(originBytes);
    }

    /**
     * 转字节数据.
     */
    private static byte[] stringToBytes(String str) {
        return str.getBytes();
    }

    /**
     * 字节数组转为字符串.
     */
    private static String bytesToString(byte[] bytes) {
        String string = null;
        try {
            string = new String(bytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return string;
    }
}
