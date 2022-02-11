package com.example.practice;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@SpringBootTest
class JumpCms {

    // http://172.25.221.55/
    private static final String url = "http://172.25.221.55/ssp_cms/#/login?code=%s&channel=%s&moduleId=%s";

    private static final String tokenCodeKey = "account=sso_test&timestamp=%s&tenantNo=01071";

    @Test
    void contextLoads() {
        try {
            long timestamp = System.currentTimeMillis();
            String orgText = String.format(tokenCodeKey, timestamp);
            System.out.println("待加密：" + orgText);
            String key = "Ssp&Test";
            String encryptText = desEncrypt(orgText, key);
            System.out.println("encryptText >> " + encryptText);
            encryptText = getBase64(encryptText);
            System.out.println("encryptText base64 encode >> " + encryptText);
            System.out.println(String.format(url, encryptText, "10001", "9"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 加密后进行Base64编码
     *
     * @param message 需要加密的消息
     * @param key     加密Key
     * @return String 加密且进行Base64编码的消息
     * @throws Exception
     */
    public static String desBase64Encrypt(String message, String key) throws Exception {
        if (StringUtils.isEmpty(message)) {
            return null;
        }
        return desEncrypt(getBase64(message), key);
    }

    /**
     * 加密
     *
     * @param message 需要加密的消息
     * @param key     加密Key
     * @return String 加密消息
     * @throws Exception
     */
    @SuppressWarnings("restriction")
    public static String desEncrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        if (StringUtils.isEmpty(message)) {
            return null;
        }

        byte[] b = cipher.doFinal(message.getBytes("UTF-8"));
        /*
         * for(int i=0;i<b.length;i++){ System.out.println(b[i]); }
         */
        sun.misc.BASE64Encoder base64encoder = new sun.misc.BASE64Encoder();
        String encryptStr = base64encoder.encode(b);
        return encryptStr;

    }

    // BASE64 加密
    public static String getBase64(String str) {

        byte[] b = null;
        String s = null;
        try {
            b = str.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            s = Base64.encodeBase64URLSafeString(b);
        }
        return s;
    }

}
