package cn.lukas.lib_java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class Test {

    private static final String key = "C9CA0269E7B1E9AA2778D84A78913DA06F94";
    private static final String CIPER_ALGORITHM = "AES/CBC/PKCS5Padding";


    public static void main(String[] args) throws Exception {

        File file_c = new File("C:\\Users\\61491\\Documents\\Samsung\\SmartSwitch\\backup\\SM-G8870\\SM-G8870_\\SM-G8870_20210120121309\\APKFILE\\com.syndu.expert.enc");
        File file_p = new File("C:\\Users\\61491\\Documents\\Samsung\\SmartSwitch\\backup\\SM-G8870\\SM-G8870_\\SM-G8870_20210120121309\\APKFILE\\com.syndu.expert.apk");
        if (file_c.exists()) {
            System.out.println("加密文件存在");
        } else {
            System.out.println("加密文件不存在");
        }
        if (file_p.exists()) {
            System.out.println("解密文件存在");
        } else {
            System.out.println("解密文件不存在");
            try {
                if (file_p.createNewFile()) {
                    System.out.println("解密文件创建成功");
                } else {
                    System.out.println("解密文件创建失败");

                }
            } catch (IOException e) {
                System.out.println("解密文件创建异常" + e);

            }
        }

//        AESFileUtil.decryptFile("F:\\SM-G8870_20201217110322\\ALARM\\ALARM\\alarm.exml" , "F:\\SM-G8870_20201217110322\\ALARM\\ALARM\\alarm.xml" , key);
//        getSHA256SecretKey(key);
//        System.out.println(generatePrivateCode());
        decryptFile(key, file_c , file_p);
    }


    private static String generatePrivateCode() {
        int[] iArr = new int[36];
        StringBuilder sb = new StringBuilder(10);
        SecureRandom secureRandom = new SecureRandom();
        int i = 0;
        while (i < 10) {
            int nextInt = secureRandom.nextInt(36);
            int i2 = iArr[nextInt];
            iArr[nextInt] = i2 + 1;
            if (i2 > 2) {
                i--;
            } else {
                sb.append("0123456789abcdefghijklmnopqrstuvwxyz".charAt(nextInt));
            }
            i++;
        }
        return sb.toString();
    }


    private static byte[] getBlock(InputStream in, int size) {
        byte[] salt = new byte[size];
        try {
            int _size = in.read(salt);
            if (_size == size) {
                return salt;
            }
            return null;
        } catch (IOException e) {
            return salt;
        }
    }


    public static void decryptFile(String password, File file_c, File file_p) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes("UTF-8"));
        byte[] bArr = new byte[16];
        System.arraycopy(digest.digest(), 0, bArr, 0, bArr.length);
        CipherInputStream cipherInputStream;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        if (file_c.exists()) {
            System.out.println("加密文件存在");
        } else {
            System.out.println("加密文件不存在");
        }
        if (file_p.exists()) {
            System.out.println("解密文件存在");
        } else {
            System.out.println("解密文件不存在");
            try {
                if (file_p.createNewFile()) {
                    System.out.println("解密文件创建成功");
                } else {
                    System.out.println("解密文件创建失败");

                }
            } catch (IOException e) {
                System.out.println("解密文件创建异常" + e);

            }
        }

        try {
            fileInputStream = new FileInputStream(file_c);
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, 0, bArr.length, "AES");
            Cipher instance = Cipher.getInstance(CIPER_ALGORITHM);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(getBlock(fileInputStream, instance.getBlockSize()));
            instance.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            FileOutputStream fileOutputStream2 = new FileOutputStream(file_p);
            cipherInputStream = new CipherInputStream(fileInputStream, instance);
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = cipherInputStream.read(bArr2);
                if (read != -1) {
                    fileOutputStream2.write(bArr2, 0, read);
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            fileOutputStream2.close();
            fileInputStream.close();
            cipherInputStream.close();
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("无此加密算法，请检查环境");
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException("明文数据未找到");
        } catch (InvalidKeyException e) {
            throw new InvalidKeyException("加密秘钥非法，请检查");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("其他错误:", e);
        }

    }



    public static SecretKeySpec getSHA256SecretKey(String password) throws Exception {
        StringBuilder oldStr = new StringBuilder();
        for (byte b : password.getBytes("UTF-8")) {
            oldStr.append(b);
            oldStr.append(",");
        }
        System.out.println("key为：" + oldStr.toString());

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(password.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        StringBuilder keyStr = new StringBuilder();
        for (byte b : keyBytes) {
            keyStr.append(b);
            keyStr.append(",");
        }
        System.out.println("SHA256为：" + keyStr.toString());
        return new SecretKeySpec(keyBytes, "AES");
    }
}
