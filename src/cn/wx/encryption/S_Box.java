package cn.wx.encryption;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


/**
 * 
 * @author WX
 *	替换表
 */
public class S_Box {
	private int[] box=new int[256];//加密时正向映射
	private int[] DeBox=new int[256];//上述映射的反向映射
 
	public S_Box(String pathname){
		File file=new File(pathname);
		BufferedReader reader=null;
		try {
			reader=new BufferedReader(new FileReader(file));
			String line;
			for(int i=0;i<256;i++) {
				line=reader.readLine();
				box[i]=Integer.parseInt(line);
				DeBox[box[i]]=i;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 * @param value
	 * @return value在映射表中映射的值（加密的步骤）
	 */
	public int EN(int value) {
		if(value<256&&value>0)
			return box[value];
		return 0;
	}
	/**
	 * 
	 * @param value
	 * @return 在对应表中映射为value的值（解密的步骤）
	 */
	public int DE(int value){
		if(value<256&&value>0)
			return DeBox[value];
		return 0;
	}
}
