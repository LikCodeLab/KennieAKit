package cn.lukas.utils.core.module.encryption;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class TextMain {


//        KeyPair keyPair = RSAUtils.generateRSAKeyPair();
//        PrivateKey aPrivate = keyPair.getPrivate();
//        PublicKey aPublic = keyPair.getPublic();
//
//        Log.e(TAG, "onCreate: " + aPrivate);
//        Log.e(TAG, "onCreate: " + aPublic);
//
//
//        byte[] bytes = RSAUtils.encryptData("aaa".getBytes(), aPublic);
//
//        String encode = Base64Utils.encode(bytes);
//        Log.e(TAG, "onCreate: 加密 : " + encode);
//
//        byte[] decode = Base64Utils.decode(encode);
//
//        String s = new String(RSAUtils.decryptData(decode, aPrivate));
//
//        Log.e(TAG, "onCreate: 解密: " + s);
//
//
//        Log.e(TAG, "onCreate: "+ SHA1Utils.SHA1("aaa"));
//        Log.e(TAG, "onCreate: "+ MD5Utils.encrypBy("aaa"));
//
//
//        StringZip.main(null);
//
//
//        String aaa = AESUtils.encrypt("AAA");
//
//        Log.e(TAG, "onCreate: aaa : "+aaa );
//
//        String decrypt = AESUtils.decrypt(aaa);
//
//        Log.e(TAG, "onCreate: aaa decrypt: "+decrypt );


    KeyPair keyPair = RSAUtils.generateRSAKeyPair();

    PrivateKey aPrivate = keyPair.getPrivate();
    PublicKey aPublic = keyPair.getPublic();

    HMacHelper hMacHelper = new HMacHelper("aaa");
    byte[] sign = hMacHelper.sign("a".getBytes());
    boolean verify = hMacHelper.verify("aaa".getBytes(), sign);

//        Log.e(TAG, "onCreate: "+  verify);


}
