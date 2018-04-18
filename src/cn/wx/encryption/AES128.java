package cn.wx.encryption;

import java.util.ArrayList;
/**
 *AES加密 CBC模式 
 *AES128中一个分组长128bit即16个字节(byte)，密钥长度也是128bit，采用10轮加密
 *一行4个字节，4行一个分组，共16个字节128位。即一个4*4矩阵
 */
public class AES128{
	
	private byte[] key;
	/**
	 * 
	 * @param key 密钥
	 */
	public AES128(byte[] key) {
		super();
		if(key.length==128)
			this.key = key;
		else
			this.key=null;
	}
	
	/**
	 * @param 传入的明文planinText
	 */
	public String Encryption(String plainText) {
		StringBuilder cipherText=new StringBuilder();
		ArrayList<byte[]> groups=Grouping(plainText);
		
		/*
		 *加密处理 
		 *
		 */
		return cipherText.toString();
	}
//  明文切割
	public static ArrayList<byte[]> Grouping(String plainText){
		byte[] text=plainText.getBytes();
		//补齐最后一个分组
		int fill=16-text.length%16;
		byte [] fText=new byte[text.length+fill];
		System.arraycopy(text, 0, fText, 0, text.length);
		for(int i=text.length;i<fText.length;i++)
			fText[i]=0;
		//分割
		ArrayList<byte []>groups=new ArrayList<>();
		byte [] group=new byte[16];
		int loc=0;
		while(loc<fText.length) {
			group[loc%16]=fText[loc];
			if((loc+1)%16==0)
				groups.add(group.clone());
			loc++;
		}
		return groups;
	}
////	字符替换
//	private byte[] SubBytes(byte[] group) {
//		return null;
//	}
////	行位移
//	private byte[] ShiftRows(byte[] fromPlainText) {
//		return null;
//	}
////	列混合
//	private byte[] MixColumns(byte[] fromShitfRows) {
//		return null;
//	}
////	密钥加密
//	private byte[] AddRoundKey(byte[] fromMixColumns){
//		return null;
//	}
	public static void main(String[] args) {
		ArrayList<byte[]> groups=AES128.Grouping("abcdefghijklmndfghkadjhgaskhdnfkljhlk");
		for(byte [] e:groups) {
			for(int i=0;i<e.length;i++)
				System.out.print(e[i]);
			System.out.println();
		}
	}

}
