package cn.wx.encryption;

import java.util.ArrayList;

import org.w3c.dom.css.ElementCSSInlineStyle;

/**
 *AES加密 CBC模式 
 *AES128一个分组长32bit,密钥长度为32bit，采用10轮加密
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
		/*
		 *加密处理 
		 *
		 */
		return cipherText.toString();
	}
// 明文切割
	private ArrayList<Byte[]> Grouping(String plainText){
		/*
		 *明文分组
		 */
		return null;
	}
//	字符替换
	private byte[] subBytes(byte[] plainText) {
		return null;
	}
//	行位移
	private byte[] shiftRows(byte[] fromPlainText) {
		return null;
	}
//	列混合
	private byte[] MixColumns(byte[] fromShitfRows) {
		return null;
	}
//	密钥加密
	private byte[] AddRoundKey(byte[] fromMixColumns){
		return null;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
