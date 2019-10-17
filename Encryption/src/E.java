import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Random;

public class E {


    byte[] skey=new byte[1000];
    String skeyString;
    static  byte[] raw;

    String inputMsg,encMsg,decMsg;

    public E(){

        try {
            generateSKey();
            inputMsg = JOptionPane.showInputDialog(null, "Message");
            byte[] ibyte = inputMsg.getBytes();
            byte[] ebyte = encrypt(raw, ibyte);
            String encMsg = new String(ebyte);

            System.out.println("E :" + encMsg);

            byte[] dec = decrypt(raw, ebyte);
            String decMsg = new String(dec);


            System.out.println(decMsg);

        }catch (Exception e){
            System.out.println(e);
        }
    }

    void generateSKey(){
        try {
            Random random=new Random();

            int num =random.nextInt(10000);
            String knum=String.valueOf(num);

            byte[] knumb=knum.getBytes();

            skey=getRawKey(knumb);

//            Key k=(Key)skey;

            skeyString=new String(skey);

            System.out.println("key :"+skeyString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private byte[] getRawKey(byte[] seed) throws Exception{
        KeyGenerator generator=KeyGenerator.getInstance("DES");

        SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed);

        generator.init(56,secureRandom);
        SecretKey secretKey=generator.generateKey();

        raw=secretKey.getEncoded();

        return raw;
    }


    private byte[] encrypt(byte[] raw, byte[] clear) throws Exception{
        SecretKeySpec keySpec=new SecretKeySpec(raw,"DES");
        Cipher cipher=  Cipher.getInstance("DES");

        cipher.init(Cipher.ENCRYPT_MODE,keySpec);

        byte[] encrypted=cipher.doFinal(clear);

        return encrypted;
    }


    private byte[] decrypt(byte[] raw, byte[] encrypted) throws  Exception{
        SecretKeySpec keySpec=new SecretKeySpec(raw, "DES");
        Cipher cipher=Cipher.getInstance("DES");

        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decry=cipher.doFinal(encrypted);

        return decry;
    }

    public static void main(String[] args) {
        E e=new E();
    }
}
