package cn.wx.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSA {
	  private PublicKey publicKey;
	  private PrivateKey privateKey;
	  private int keyLen;
	  
	  public RSA(int KeyLen) {
		  this.keyLen=KeyLen;
	  }
	  //更新密钥
	  public void updateKey() {
		  try {
				KeyPair keyPair=genKeyPair(1024);
				publicKey=keyPair.getPublic();
				privateKey=keyPair.getPrivate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  //获取公钥
	  public PublicKey getPublicKey() {
		  return publicKey;
	  }
	  //获取私钥
	  public PrivateKey getPrivateKey() {
		  return privateKey;
	  }
	  //生成密钥对  
	  private KeyPair genKeyPair(int keyLength) throws Exception{  
		  KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance("RSA");  
		  keyPairGenerator.initialize(keyLength);        
		  return keyPairGenerator.generateKeyPair();  
	  }
	  //公钥加密  
	  public byte[] encrypt(byte[] content) throws Exception{  
	       Cipher cipher=Cipher.getInstance("RSA");//java默认"RSA"="RSA/ECB/PKCS1Padding"  
	       cipher.init(Cipher.ENCRYPT_MODE, publicKey);  
	       return cipher.doFinal(content);  
	   }  
	      
	  //私钥解密  
	  public byte[] decrypt(byte[] content) throws Exception{  
	      Cipher cipher=Cipher.getInstance("RSA");  
	      cipher.init(Cipher.DECRYPT_MODE, privateKey);  
	      return cipher.doFinal(content);  
       }  
}
