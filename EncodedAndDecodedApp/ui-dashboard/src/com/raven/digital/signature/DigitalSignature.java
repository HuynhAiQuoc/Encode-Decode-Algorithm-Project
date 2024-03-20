package com.raven.digital.signature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Huynh Ai Quoc
 */
public class DigitalSignature {
    
    public DigitalSignature(){
        super();
    }
    
    public static PrivateKey readPrivateKey(String path) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException{
        String privateKeyBase64 = FileUtils.readFileToString(new File(path), "utf-8");
        byte[] encriptKey = Base64.getDecoder().decode(privateKeyBase64);
        
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encriptKey);
        KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
        PrivateKey prvKey = keyFactory.generatePrivate(privateKeySpec);
        return prvKey;
        
    }
    
    public static void createSignature(PrivateKey priv, String input, String output){
        try {
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
            dsa.initSign(priv);
            FileInputStream fis = new FileInputStream(input);
            BufferedInputStream bufin = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int len;
            while((len = bufin.read(buffer)) != -1){
                dsa.update(buffer, 0, len);
            }
            bufin.close();
            byte[] realSig = dsa.sign();
            
            //save signature
            FileOutputStream sigfos = new FileOutputStream(output);
            sigfos.write(realSig);
            sigfos.flush();
            sigfos.close();
        } catch(IOException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException e){
            System.err.println("Caught exception " + e.toString());
        }
    }

}
