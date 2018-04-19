package cn.wx.security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD {
	public static byte[] getSHA2(String str) {
		try {
//			1 获取md5
//			MessageDigest md5=MessageDigest.getInstance("MD5");
//			2 加密
//			md5.update(str.getBytes());
//			3 获取加密后的信息
//			return md5.digest().toString();
			return MessageDigest.getInstance("SHA-256").digest(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
