package cn.wx.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.logging.Logger;

import cn.wx.map.CrossRoads;
import cn.wx.map.Point;

public class Util {
	//从文件中读出所有组成地图的点
	public static ArrayList<Point> readNodes(String fileName){
		ArrayList<Point> lists=new ArrayList<>();
		BufferedReader bufferedReader = null;
		File file=new File(fileName);
		try {
			FileReader reader=new FileReader(file);
			bufferedReader=new BufferedReader(reader);
			String line;
			while((line=bufferedReader.readLine())!=null) {
				Point point=parseToPoint(line);
				lists.add(point);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lists;
	}
	//从文件中读出地图信息
	public static ArrayList<CrossRoads> readMap(String fileName) {
		ArrayList<CrossRoads> lists=new ArrayList<>();
		CrossRoads crossRoads=null;
		BufferedReader bufferedReader = null;
		File file=new File(fileName);
		try {
			FileReader reader=new FileReader(file);
			bufferedReader=new BufferedReader(reader);
			String line;
			while((line=bufferedReader.readLine())!=null) {
				crossRoads=new CrossRoads();
				/*每行解析为一个路口*/
				int q=0,h=0;
				while((q=line.indexOf('(', h))!=-1) {
					h=line.indexOf(')',q);
					Point point=parseToPoint(line.substring(q,h+1));
					if(crossRoads.getPoint()==null)crossRoads.setPoint(point);
					else crossRoads.getPoints().add(point);
				}
				lists.add(crossRoads);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(bufferedReader!=null)
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return lists; 
	}
	//将一个(x,y)字符串解析为一个Point对象
	public static Point parseToPoint(String str) {
		int q=str.indexOf('(');
		int z=str.indexOf(',');
		int h=str.indexOf(')');
		if(q==-1||z==-1||h==-1)
			return null;
		Point point=new Point();
		point.setX(Double.parseDouble(str.substring(q+1,z)));
		point.setY(Double.parseDouble(str.substring(z+1,h)));
		return point;
	}
	//计算两点间距离
	public static double distance(Point p1,Point p2) {
		double dx=p1.getX()-p2.getX();
		double dy=p1.getY()-p2.getY();
		return Math.sqrt(dx*dx+dy*dy);
	}
	public static void outBytes(byte[] bytes,String name) {
		String msg = name;
		for(byte e:bytes) {
			msg=msg+"\t"+TypeConversion.byteToInt(e);
		}
		System.out.println(msg);
	}
}
