package com.savchuk.services;

import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/**
 * Created by home on 08.05.17.
 */
@Service
public class CryptoService {

    private static String PASSWORD_SALT_STRING = "MAY SALT";

    private static String AES_PASSWORD = "MAY PASSWORD";

    public static String getSha512Token(String passwordToHash, String   salt){
        salt = salt == null ? PASSWORD_SALT_STRING : salt;
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static String pad2(String n) {
        if (n.length() < 2) {
            return "0" + n;
        } else {
            return n;
        }
    }

    private static String hex(byte[] bytes) {
        String r = "";
        for (int i = 0; i < bytes.length; i++) {
            r = r + pad2(Integer.toHexString(bytes[i] + 128));
        }
        return r;
    }

    private static String safePassword(String unsafe) {
        String safe = unsafe;
        if (safe.length() > 16) {
            safe = safe.substring(0, 16);
        }
        int nn = safe.length();
        for (int i = nn - 1; i < 15; i++) {
            safe = safe + "*";
        }
        return safe;
    }

    public static String encryptAes(String value){
        String password = AES_PASSWORD;
        try {

            SecretKey key = new SecretKeySpec(safePassword(password).getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(value.getBytes("UTF-8"));
            return hex(encrypted);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int parseInt2(String s) {
        return (new java.math.BigInteger(s, 16)).intValue();
    }

    private static byte[] fromHex(String enc) {
        byte[] r = new byte[enc.length() / 2];
        for (int i = 0; i < r.length; i++) {
            int n = parseInt2(enc.substring(i * 2, i * 2 + 2)) - 128;
            r[i] = (byte) n;
        }
        return r;
    }

    public static String decryptAes(String value){
        String password = AES_PASSWORD;
        try {
            byte[] encypted = fromHex(value);
            SecretKey key = new SecretKeySpec(safePassword(password).getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(encypted);
            return new String(decrypted, "UTF-8");
        }catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
