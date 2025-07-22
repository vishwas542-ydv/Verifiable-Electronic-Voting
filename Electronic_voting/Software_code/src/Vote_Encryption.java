import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;



public class Vote_Encryption {
    public void generateVoterKey(int keySize) throws Exception {
        String AESFilename = "V://Project_GIT//project//Keys//AESkey.txt";
        String IVFilename = "V://Project_GIT//project//Keys//IV.txt";
        SecretKey AESkey_ref;
        byte[] IV = new byte[16];
        DataPoint dp = new DataPoint();
        SecureRandom srand=new SecureRandom();
        srand.nextBytes(IV);

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keySize);
        AESkey_ref = keyGen.generateKey();
        System.out.println(AESkey_ref);

        String encodedkey = Base64.getEncoder().encodeToString(AESkey_ref.getEncoded());
        String encodedIV = Base64.getEncoder().encodeToString(IV);
        System.out.println(encodedkey);
        dp.writeKeys(encodedkey, AESFilename);
        dp.writeIV(encodedIV, IVFilename);
    }


    public String encrypt(byte[] IV, String plainText, byte[] encryptionKey) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptionKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV));
        byte[] CipherVote = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(CipherVote);
    }

    public String Decrypt(String Encrypted_File ) throws Exception
    {
        DataPoint dp = new DataPoint();
        byte[] IV = dp.AESreadIV();
        byte[] encryptedKey = dp.AESreadKeys();
        byte[] DecodedData = dp.readDecodedData(Encrypted_File);




        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(encryptedKey, "AES");
        cipher.init(Cipher.DECRYPT_MODE, key,new IvParameterSpec(IV));
        return new String(cipher.doFinal(DecodedData),"UTF-8");
    }


}
