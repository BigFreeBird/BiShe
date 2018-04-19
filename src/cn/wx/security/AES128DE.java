package cn.wx.security;

import java.util.ArrayList;

import cn.wx.factory.S_BoxFactory;
import cn.wx.util.BitOperation;
import cn.wx.util.TypeConversion;

public class AES128DE {
	private byte[] key=new byte[16];
	private byte[] initVector=new byte[16];
	
	public AES128DE(byte []key,byte [] initVector){
		this.key=key;
		this.initVector=initVector;
	}
	public byte[] DeCrypt(byte[] cipher) {
		ArrayList<byte []> groups=grouping(cipher);
		byte[] plainText=new byte[cipher.length];
		byte[] vector=initVector;
		for(int i=0;i<groups.size();i++) {
			byte[] group=groups.get(i);
			byte []tv=group.clone();
			group=deAddRoundKey(group);//两次XOR操作，还原位原字节数组
			group=deMixColumns(group);
			group=deShiftRows(group);
			group=deSubBytes(group);
			group=BitOperation.bitXOR(group, vector);
			vector=tv;
			System.arraycopy(group, 0, plainText, i*16, 16);
		}
		return plainText;
	}
//  text切割,切割成每组16个字节
	private ArrayList<byte[]> grouping(byte[] cipherText){

		//分割
		ArrayList<byte []>groups=new ArrayList<>();
		
		int loc=0;
		while(loc<cipherText.length) {
			byte [] group=new byte[16];
			System.arraycopy(cipherText, loc, group, 0, 16);
			groups.add(group);
			loc+=16;
		}
		return groups;
	}

//	1 SubByte字符替换
	private byte[] deSubBytes(byte[] group) {
		byte [] DE=new byte[group.length];
		S_Box box=S_BoxFactory.getS_Box();
		for(int i=0;i<group.length;i++)
			DE[i]=(byte) box.DE(TypeConversion.byteToInt(group[i]));
		return DE;
	}
//	2 ShiftRows 一组中的16个字节分成四行， 第i行移动3*i+10位
	private byte[] deShiftRows(byte[] group) {
		byte[] des=new byte[group.length];
		for(int i=0;i<4;i++){
			byte [] line=new byte[4];
			System.arraycopy(group, i*4, line, 0, 4);
			int iline=TypeConversion.byteArrayToInt(line);
			iline=BitOperation.leftMove(iline, 22-3*i);
			line=TypeConversion.intToByteArray(iline);
			System.arraycopy(line, 0, des, i*4, 4);
		}
		return des;
	}
//	3 列混合(列混合应使用矩阵乘法实现，此处在为降低加密和解密的复杂性，用列的位移代替)
	private byte[] deMixColumns(byte[] group) {
		return group;
	}
//	4 AddRoundKey密钥加密
	private byte[] deAddRoundKey(byte[] group){
		return BitOperation.bitXOR(group, key);
	}
}
