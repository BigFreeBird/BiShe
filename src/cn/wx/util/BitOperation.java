package cn.wx.util;



public class BitOperation {
	/**
	 * 位移后，最后的位移动到前面
	 * @param num
	 * @param i
	 * @return
	 */
	public static int leftMove(int num,int i) {
		int des=num;
		des=des<<i;
		//des中的前i个位置是num中最后i个位置
		for(int desloc=i-1;desloc>0;desloc--) {
			int srcloc=32+desloc-i;
			des=setBit(des, desloc, getBit(num,srcloc));
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
			src[i]=(byte) (src[i]^mask[1]);
		return src;
	}
}
