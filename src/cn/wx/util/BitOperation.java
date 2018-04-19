package cn.wx.util;



public class BitOperation {
	/**
	 * src中最左侧的i位在des中位置 是(srcloc+i)%32
	 * 位移后，最后的位移动到前面
	 * @param src
	 * @param i
	 * @return
	 */
	public static int leftMove(int src,int i) {
		i=i%32;
		int des=src;
		des=des<<i;
		//des中的前i个位置是num中最后i个位置
		for(int srcloc=31;srcloc>31-i;srcloc--) {
			int desloc=(srcloc+i)%32;
			des=setBit(des, desloc, getBit(src, srcloc));
		}
		return des;
		//保存最后num个
		
	}
	/**
	 * 
	 * @param b
	 * @param num
	 * @return b的第num个位 的值	true表示1，false表示0
	 */
	public static boolean getBit(int num,int i) {
		
		return ((1<<i)&num)!=0;
	}
	
	/**
	 * 
	 * @param num
	 * @param i
	 * @param one
	 * @return 把num的第i位设置位0或1（one==true时表示1，one==false时表示0）
	 */
	public static int setBit(int num,int i,boolean one) {
		if(one)
			return num|1<<i;
		else
			return num&(~(1<<i));
	}
	
	public static byte[] bitXOR(byte [] src,byte[] mask) {
		for(int i=0;i<src.length;i++)
			src[i]=(byte) (src[i]^mask[i]);
		return src;
	}
	public static void toByte(int v) {
		String byString="";
		for(int i=31;i>=0;i--) {
			if((i+1)%4==0)
				byString+=" ";
			if(getBit(v, i))
				byString+="1";
			else
				byString+="0";
		}
		System.out.println(byString);
	}
}
