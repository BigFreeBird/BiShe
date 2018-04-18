package cn.wx.factory;

import cn.wx.encryption.S_Box;

public class S_BoxFactory {
	private S_Box box;
	public S_Box getS_Box(String pathname) {
		if(box!=null)
			return box;
		return new S_Box(pathname);
	}
}
