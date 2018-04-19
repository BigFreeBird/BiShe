package cn.wx.security;

import java.util.ArrayList;

import cn.wx.factory.S_BoxFactory;
import cn.wx.util.BitOperation;
import cn.wx.util.TypeConversion;
/**
 *AES加密 CBC模式 
 *AES128中一个分组长128bit即16个字节(byte)，密钥长度也是128bit，采用10轮加密
 *一行4个字节，4行一个分组，共16个字节128位。即一个4*4矩阵
 *1 SubByte 使用txtfile/S-box.txt中的映射表，行值-1表示加密时的输入映射 
 *2 ShiftRows 第i行左移i*3+10
 *3 MixColone不操作
 *4 AddRoundKey
 */
public class AES128EN{
	
	private byte[] key=new byte[16];
	private byte[] initVector=new byte[16];
	/**
	 * 
	 * @param key 密钥
	 */
	public AES128EN(String token) {
		byte []md=MD.getSHA2(token);
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
	public byte[] Encryption(byte[] plainText) {
		/*
		 *加密处理 
		 *先使用ECB模式
		 */
		ArrayList<byte[]> groups=grouping(plainText);
		byte[] cipherText=new byte[groups.size()*16];
		byte[] vector=initVector;
		for(int i=0;i<groups.size();i++) {
			byte[] group=new byte[16];
			System.arraycopy(groups.get(i), 0, group, 0, 16);
			//group和vector异或
			group=BitOperation.bitXOR(group, vector);
			group=subBytes(group);
			group=shiftRows(group);
			group=mixColumns(group);
			group=addRoundKey(group);
			vector=group.clone();
			System.arraycopy(group, 0, cipherText, i*16, 16);
		}
		return cipherText;
	}


	
//  text切割,切割成每组16个字节,最后一组不够16个字节则补空
	private ArrayList<byte[]> grouping(byte [] text){
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
	private byte[] subBytes(byte[] group) {
		byte [] EN=new byte[group.length];
		S_Box box=S_BoxFactory.getS_Box();
		for(int i=0;i<group.length;i++)
			EN[i]=(byte) box.EN(TypeConversion.byteToInt(group[i]));
		return EN;
	}
//	2 ShiftRows 一组中的16个字节分成四行， 第i行移动3*i+10位
	private byte[] shiftRows(byte[] group) {
		byte[] des=new byte[group.length];
		for(int i=0;i<4;i++){
			byte [] line=new byte[4];
			System.arraycopy(group, i*4, line, 0, 4);
			int iline=TypeConversion.byteArrayToInt(line);
			iline=BitOperation.leftMove(iline, 3*i+10);
			line=TypeConversion.intToByteArray(iline);
			System.arraycopy(line, 0, des, i*4, 4);
		}
		return des;
	}
//	3 列混合(列混合应使用矩阵乘法实现，此处在为降低加密和解密的复杂性，用列的位移代替)
	private byte[] mixColumns(byte[] group) {
		return group;
	}
//	4 AddRoundKey密钥加密
	private byte[] addRoundKey(byte[] group){
		return BitOperation.bitXOR(group, key);
	}
}
