import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

/*
 * In Cache
 * USER>s val = $system.Encryption.AESCBCEncrypt("Hello World", "Bar12345Bar12345", "0000000000000000")
 * USER>s val64 = $system.Encryption.Base64Encode(val)                              
 * USER>w val64                                                                    
 * xdiuWslVlkfqIFcznmmv+w==
 */

public class AESCBCTestDecrypt {
    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] decoded = new BASE64Decoder().decodeBuffer(encrypted);
            byte[] original = cipher.doFinal(decoded);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        String key = "Bar12345Bar12345"; 
        String iv = "0000000000000000"; 
        String encrypted = "xdiuWslVlkfqIFcznmmv+w==";
        System.out.println(decrypt(key, iv,encrypted ));
    }
}
