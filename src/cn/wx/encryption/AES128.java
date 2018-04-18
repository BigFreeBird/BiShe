package cn.wx.encryption;

import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import cn.wx.factory.S_BoxFactory;
import cn.wx.util.BitOperation;
import cn.wx.util.TypeConversion;
/**
 *AES加密 CBC模式 
 *AES128中一个分组长128bit即16个字节(byte)，密钥长度也是128bit，采用10轮加密
 *一行4个字节，4行一个分组，共16个字节128位。即一个4*4矩阵
 */
public class AES128{
	
	private byte[] key=new byte[16];
	private byte[] initVector=new byte[16];
	/**
	 * 
	 * @param key 密钥
	 */
	public AES128(String str) {
		byte []md=MD.getSHA2(str);
		System.arraycopy(md, 0, key, 0, 16);
		System.arraycopy(md, 16, initVector, 0, 16);
	}
	
	public byte[] getKey() {
		return key;
	}
	public byte[] getInitVector() {
		return initVector;
	}
	/**
	 * @param 传入的明文planinText
	 */
	public String Encryption(String plainText) {
		StringBuilder cipherText=new StringBuilder();
		byte[] vector=initVector;
		/*
		 *加密处理 
		 *先使用ECB模式
		 */
		ArrayList<byte[]> groups=Grouping(plainText);
		for(byte[] group:groups) {
			BitOperation.bitXOR(group, vector);
			group=SubBytes(group);
			group=ShiftRows(group);
			group=MixColumns(group);
			group=AddRoundKey(group);
			vector=group.clone();
			cipherText.append(group.toString());
		}
		return cipherText.toString();
	}
//  明文切割,切割成每组16个字节
	private ArrayList<byte[]> Grouping(String plainText){
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
//	1 SubByte字符替换
	private byte[] SubBytes(byte[] group) {
		byte [] EN=new byte[group.length];
		S_Box box=S_BoxFactory.getS_Box();
		for(int i=0;i<group.length;i++)
			EN[i]=(byte) box.EN(group[i]);
		return EN;
	}
//	2 ShiftRows 一组中的16个字节分成四行， 第i行移动2*i位
	private byte[] ShiftRows(byte[] group) {
		byte[] des=new byte[group.length];
		for(int i=0;i<4;i++){
			byte [] line=new byte[4];
			System.arraycopy(group, i*4, line, 0, 4);
			int iline=TypeConversion.byteArrayToInt(line);
			iline=BitOperation.leftMove(iline, 2*i);
			line=TypeConversion.intToByteArray(iline);
			System.arraycopy(line, 0, des, i*4, 4);
		}
		return des;
	}
//	3 列混合(列混合应使用矩阵乘法实现，此处在为降低加密和解密的复杂性，用列的位移代替)
	private byte[] MixColumns(byte[] group) {
		return group;
	}
//	4 AddRoundKey密钥加密
	private byte[] AddRoundKey(byte[] group){
		return BitOperation.bitXOR(group, key);
	}
	public static void main(String[] args) {
		AES128 aes=new AES128("wangxiang");
		System.out.println(aes.Encryption("我是明文"));
		System.out.println(MD.getSHA2("我是明文"));
	}

}
