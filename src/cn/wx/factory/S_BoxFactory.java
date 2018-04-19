package cn.wx.factory;

import cn.wx.security.S_Box;

public class S_BoxFactory {
	private static S_Box box;
	public static S_Box getS_Box() {
		if(box!=null)
			return box;
		return new S_Box("txtFile/S-box.txt");
	}
}
