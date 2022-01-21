package test;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author LukasLee
 * @time 2020/3/30 15:04
 * @classname DateUtils
 * description:压缩zip4j 工具测试项目  解压文件
 * <p>
 * https://blog.csdn.net/u010889616/article/details/78946575
 * <p>
 * https://blog.csdn.net/weixin_43914888/article/details/101549748
 * <p>
 * https://blog.csdn.net/u012527802/article/details/80392307
 */
@SuppressWarnings("unused")
public class ZipTest {

    private static final String key = "8D705B447E3EDDCAF1510AADA351D5FFEE7D";
    private static final String CIPER_ALGORITHM = "AES/CBC/PKCS5Padding";


    public static void main(String[] args) throws Exception {

//        unZipToFolder("F:\\SM-G8870_20201217110322\\ALARM\\ALARM.zip", "F:\\SM-G8870_20201217110322\\ALARM", "");
//        String key  =PEncryptionManager.getInstance().getSHA256SecretKey("111").toString();
//        System.out.println("密码数据为：" + key);
//        MessageDigest digest = MessageDigest.getInstance("SHA-256");
//        StringBuilder oldKey = new StringBuilder();
//        byte[] k = hexStrToByteArray(key);
//        for(byte b : k){
//            oldKey.append(b);
//            oldKey.append(",");
//        }
//        System.out.println("密码长度为：" + k.length);
//        System.out.println(oldKey.toString());
//        digest.update(key.getBytes("UTF-8"));
//        System.out.println("密码长度为：" + digest.getDigestLength());
//        StringBuilder newKey = new StringBuilder();
//        for(byte b : digest.digest()){
//            newKey.append(b);
//            newKey.append(",");
//        }
//        System.out.println(newKey.toString());
//        System.out.println(newKey.toString().substring(0,16));
        System.out.println(generatePrivateCode());
        File file_c = new File("F:\\SM-G8870_20201217110322\\ALARM\\ALARM\\alarm.exml");
        File file_p = new File("F:\\SM-G8870_20201217110322\\ALARM\\ALARM\\alarm.xml");
//        decryptFile(key, file_c, file_p);
//        decrypt(file_c, file_p, key);
        decrypt_file0();
//        StringBuilder sbHex = new StringBuilder();
//        int value = 0;
//        int sum = 0;
//        InputStream is = new FileInputStream(file_c);
//        while ((value = is.read()) != -1) {
//            if (sum < 10) {
//                sum += 1;
//                System.out.println(Integer.toBinaryString(value));
//                sbHex.append(String.format("%02X ", value));
//            }
//        }
//        test();

    }


    public static void test(){
        byte[] bArr =hexStrToByteArray(key);
        File file_c = new File("F:\\SM-G8870_20201217110322\\ALARM\\alarm.exml");
        File file_p = new File("F:\\SM-G8870_20201217110322\\ALARM\\alarm.xml");
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
        CipherInputStream cipherInputStream;
        FileInputStream fileInputStream;
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, 0, bArr.length, "AES");
            Cipher instance = Cipher.getInstance("AES");
            instance.init(2, secretKeySpec);
            fileInputStream = new FileInputStream(file_c);
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
                    } catch (Exception unused) {
                    }
                }
            }
            fileOutputStream2.close();
            fileInputStream.close();

        }catch (Exception e){
            e.printStackTrace();
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

    public static byte[] hexStrToByteArray(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) Integer.parseInt(str.substring(i2, i2 + 2), 16);
        }
        return bArr;
    }


    /**
     * 解压
     *
     * @param zip_file  压缩包文件
     * @param dest      目标文件
     * @param secureKey 密码
     * @return
     */
    public static boolean unZipToFolder(String zip_file, String dest, String secureKey) {

        try {
            ZipFile zipFile = new ZipFile(zip_file);
            if (zipFile.isValidZipFile()) {
                System.out.println("是压缩文件 ");
            } else {
                System.out.println("压缩文件不合法，可能已经损坏！ ");
            }
            File destDir = new File(dest);
            if (!destDir.exists()) {
                destDir.mkdirs();
            }
            List<FileHeader> headers = zipFile.getFileHeaders();
            if (headers != null) {
                for (FileHeader header : headers) {
                    zipFile.extractFile(header, dest);
                }
            } else {
                System.out.println(String.format("unzipToFolder : headers is null [%s]", new Object[]{zipFile}));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


//        try {
//            ZipFile zFile = new ZipFile(zipFile);
//            if (!zFile.isValidZipFile()) {
//                throw new ZipException("This is an broken zip file");
//            }
//            File destDir = new File(dest);
//            if (!destDir.exists()) {
//                destDir.mkdirs();
//            }
//            if (zFile.isEncrypted() && !TextUtils.isEmpty(secureKey)) {
//                zFile.setPassword(secureKey);
//            }
//            List<FileHeader> headers = zFile.getFileHeaders();
//            if (headers != null) {
//                for (FileHeader header : headers) {
//                    zFile.extractFile(header, dest);
//                }
//            } else {
////                CRLog.m70w(TAG, String.format("unzipToFolder : headers is null [%s]", new Object[]{zipFile}), true);
//            }
//            return true;
//        } catch (ZipException e) {
////            CRLog.m56e(TAG, "unzipToFolder exception: " + e.toString());
//            return false;
//        }
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


    public static void decrypt(File inFile, File outFile, String saveKey) throws Exception {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;

        if (inFile.exists()) {
            System.out.println("加密文件存在");
        } else {
            System.out.println("加密文件不存在");
        }
        if (outFile.exists()) {
            System.out.println("解密文件存在");
        } else {
            System.out.println("解密文件不存在");
            try {
                if (outFile.createNewFile()) {
                    System.out.println("解密文件创建成功");
                } else {
                    System.out.println("解密文件创建失败");

                }
            } catch (IOException e) {
                System.out.println("解密文件创建异常" + e);

            }
        }

        try {
            InputStream decryptStream = decryptStream(new FileInputStream(inFile), saveKey);
            FileOutputStream fileOutputStream2 = new FileOutputStream(outFile);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = decryptStream.read(bArr, 0, 1024);
                if (read != -1) {
                    fileOutputStream2.write(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            fileOutputStream2.close();
            if (decryptStream != null) {
                try {
                    decryptStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("无此加密算法，请检查环境");
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException("明文数据未找到");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new InvalidKeyException("加密秘钥非法，请检查");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("其他错误:");
        }

    }


    public static InputStream decryptStream2(InputStream in, String saveKey) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPER_ALGORITHM);
        byte[] bArr = new byte[cipher.getBlockSize()];
        in.read(bArr);
        byte[] bytes2 = "1234567890abcdef".getBytes();
        String ivParameter = "0123456789abcdef";
        IvParameterSpec PARAM_SPEC = new IvParameterSpec(toBytes("26c7d1d26c142de0a3b82f7e8f90860a", 16));

        cipher.init(Cipher.DECRYPT_MODE, getSHA256SecretKey(saveKey), PARAM_SPEC);
        return new CipherInputStream(in, cipher);
    }


    public static InputStream decryptStream(InputStream in, String saveKey) throws Exception {
        SecretKeySpec key;
        Cipher cipher = Cipher.getInstance(CIPER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(getBlock(in, cipher.getBlockSize()));
        key = getSHA256SecretKey(saveKey);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        return new CipherInputStream(in, cipher);
    }


    public static byte[] toBytes(String str, int i) throws IllegalArgumentException, NumberFormatException {
        if (str == null) {
            return null;
        }
        if (i == 16 || i == 10 || i == 8) {
            int i2 = i == 16 ? 2 : 3;
            int length = str.length();
            if (length % i2 != 1) {
                int i3 = length / i2;
                byte[] bArr = new byte[i3];
                for (int i4 = 0; i4 < i3; i4++) {
                    int i5 = i4 * i2;
                    bArr[i4] = (byte) Short.parseShort(str.substring(i5, i5 + i2), i);
                }
                return bArr;
            }
            throw new IllegalArgumentException("For input string: \"" + str + "\"");
        }
        throw new IllegalArgumentException("For input radix: \"" + i + "\"");
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


    public static SecretKeySpec getSHA256SecretKey(String key) throws Exception {

        StringBuilder oldStr = new StringBuilder();
        for (byte b : key.getBytes("UTF-8")) {
            oldStr.append(b);
            oldStr.append(",");
        }
        System.out.println("key为：" + oldStr.toString());
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(key.getBytes("UTF-8"));
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


    public static void decrypt_file0() throws Exception {
        File file_c = new File("F:\\SM-G8870_20201217110322\\ALARM\\ALARM\\alarm.exml");
        File file_p = new File("F:\\SM-G8870_20201217110322\\ALARM\\ALARM\\alarm.xml");
        // 分析文件
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


        /**********解密文件**********/
        // 获取Dummy的byte
        StringBuilder oldKey = new StringBuilder();
        byte[] old_key_b;
        try {
            old_key_b = key.getBytes("UTF-8");
            for (byte bd : old_key_b) {
                oldKey.append(bd);
                oldKey.append(",");
            }
            System.out.println("key为：" + oldKey.toString());
            // SHA256 输出16位
            // AES128-CBC
            Cipher cipherAes = Cipher.getInstance(CIPER_ALGORITHM);
            InputStream inputStream = new FileInputStream(file_c);
            byte[] ivByte = getBlock(inputStream, cipherAes.getBlockSize());
            System.out.println("iv 16字节数据");
            System.out.println(Arrays.toString(ivByte));
            IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
            cipherAes.init(Cipher.DECRYPT_MODE, getSHA256SecretKey(key),ivParameterSpec);
            InputStream decryptStream = new CipherInputStream(inputStream, cipherAes);
            FileOutputStream fileOutputStream2 = new FileOutputStream(file_p);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = decryptStream.read(bArr, 0, 1024);
                if (read != -1) {
                    fileOutputStream2.write(bArr, 0, read);
                } else {
                    try {
                        break;
                    } catch (Exception unused) {
                        unused.printStackTrace();
                        System.out.println("异常0");

                    }
                }
            }
            inputStream.close();
            fileOutputStream2.close();
            decryptStream.close();
        } catch (NoSuchAlgorithmException e) {
            throw new NoSuchAlgorithmException("无此加密算法，请检查环境");
        } catch (NoSuchPaddingException e) {
            throw new NoSuchPaddingException("明文数据未找到");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new InvalidKeyException("加密秘钥非法，请检查");
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("其他错误:");
        }

    }

    private static byte[] getFileIv() throws IOException {
        File file = new File("F:\\SM-G8870_20201217110322\\ALARM\\alarm.exml");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] fileIv = new byte[(int) file.length()];
        int value = 0;
        int i = 0;
        while (value != -1) {
            value = fileInputStream.read(fileIv);
            i++;
        }
        return fileIv;
    }


    /**
     * @param hexString
     * @return 将十六进制转换为字节数组
     */

    private static String hexStr = "0123456789ABCDEF"; //全局

    public static byte[] HexStringToBinary(String hexString) {

        //hexString的长度对2取整，作为bytes的长度

        int len = hexString.length() / 2;

        byte[] bytes = new byte[len];

        byte high = 0;//字节高四位

        byte low = 0;//字节低四位

        for (int i = 0; i < len; i++) {

            //右移四位得到高位

            high = (byte) ((hexStr.indexOf(hexString.charAt(2 * i))) << 4);

            low = (byte) hexStr.indexOf(hexString.charAt(2 * i + 1));

            bytes[i] = (byte) (high | low);//高地位做或运算

        }

        return bytes;

    }

}

