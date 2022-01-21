package test;

import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PEncryptionManager {

    private static PEncryptionManager mInstance = null;

    private static final String CIPER_ALGORITHM = "AES/CBC/PKCS5Padding";


    public static synchronized PEncryptionManager getInstance() {
        PEncryptionManager pEncryptionManager;
        synchronized (PEncryptionManager.class) {
            if (mInstance == null) {
                mInstance = new PEncryptionManager();
            }
            pEncryptionManager = mInstance;
        }
        return pEncryptionManager;
    }


    public void decrypt(File inFile, File outFile, String saveKey) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream ;
        try {
            fileInputStream = new FileInputStream(inFile);
            fileOutputStream = new FileOutputStream(outFile);

        } catch (Exception e) {

        }

    }

    private InputStream decryptStream(InputStream in, String saveKey) throws Exception {
        SecretKeySpec key;
        Cipher cipher = Cipher.getInstance(CIPER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(getBlock(in, cipher.getBlockSize()));
        key = getSHA256SecretKey(saveKey);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        return new CipherInputStream(in, cipher);
    }

    private byte[] getBlock(InputStream in, int size) {
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

    public SecretKeySpec getSHA256SecretKey(String password) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(password.getBytes("UTF-8"));
        byte[] keyBytes = new byte[16];
        System.arraycopy(digest.digest(), 0, keyBytes, 0, keyBytes.length);
        return new SecretKeySpec(keyBytes, "AES");
    }
}
