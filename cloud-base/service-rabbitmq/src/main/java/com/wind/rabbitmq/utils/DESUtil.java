package com.wind.rabbitmq.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created with IntelliJ IDEA.
 * @Author: v_vwlkunli
 * @Date: 2022/02/22/11:36
 * @Description:
 */
@Slf4j
public class DESUtil {

    /**
     * 生成随机密钥
     *
     * @param size
     *   位数
     * @return
     */
    public static String generateRandomKey(int size) {
        StringBuilder key = new StringBuilder();
        String chars = "ABCDEFGHIJKLMN1234567890";
        for (int i = 0; i < size; i++) {
            int index = (int) (Math.random() * (chars.length() - 1));
            key.append(chars.charAt(index));
        }
        return key.toString();
    }

    /**
     * 加密
     *
     * @param inStr     需要加密的内容
     * @param secretKey 密钥
     * @return 加密后的数据
     */

    public static String encrypt(String inStr, String secretKey) {

        SecretKey deskey = new SecretKeySpec(secretKey.getBytes(), "DESede");

        Cipher cipher;

        String outStr = null;

        try {

            cipher = Cipher.getInstance("DESede");

            cipher.init(Cipher.ENCRYPT_MODE, deskey);

            outStr = byte2hex(cipher.doFinal(inStr.getBytes()));

        } catch (Exception e) {
            log.error("3DES加密异常:{}", e.getMessage());
            e.printStackTrace();
        }
        return outStr;

    }

    /**
     * 解密
     *
     * @param inStr     需要解密的内容
     * @param secretKey 密钥
     * @return 解密后的数据
     */

    public static String decrypt(String inStr, String secretKey) {

        SecretKey deskey = new SecretKeySpec(secretKey.getBytes(), "DESede");

        Cipher cipher;
        String outStr = null;

        try {

            cipher = Cipher.getInstance("DESede");

            cipher.init(Cipher.DECRYPT_MODE, deskey);

            outStr = new String(cipher.doFinal(hex2byte(inStr)));

        } catch (Exception e) {
            log.error("3DES解密异常:{}", e.getMessage());
            e.printStackTrace();
        }
        return outStr;

    }

    /**
     * 转化为16进制字符串方法
     *
     * @param digest 需要转换的字节组
     * @return 转换后的字符串
     */

    private static String byte2hex(byte[] digest) {

        StringBuffer hs = new StringBuffer();

        String stmp = "";

        for (int n = 0; n < digest.length; n++) {

            stmp = Integer.toHexString(digest[n] & 0XFF);

            if (stmp.length() == 1) {

                hs.append("0" + stmp);

            } else {

                hs.append(stmp);

            }

        }

        return hs.toString().toUpperCase();

    }

    /**
     * 十六进转二进制
     *
     * @param hexStr 待转换16进制字符串
     * @return 二进制字节组
     */

    public static byte[] hex2byte(String hexStr) {

        if (hexStr == null)

            return null;

        hexStr = hexStr.trim();

        int len = hexStr.length();

        if (len == 0 || len % 2 == 1)

            return null;

        byte[] digest = new byte[len / 2];

        try {

            for (int i = 0; i < hexStr.length(); i += 2) {

                digest[i / 2] = (byte) Integer.decode("0x" + hexStr.substring(i, i + 2)).intValue();

            }
            return digest;

        } catch (Exception e) {

            return null;

        }

    }


}
