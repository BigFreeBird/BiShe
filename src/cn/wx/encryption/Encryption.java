package cn.wx.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
	//获取摘要
	public String getMD5(String message) {
		try {
			return MessageDigest.getInstance("MD5").digest(message.getBytes()).toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
}

