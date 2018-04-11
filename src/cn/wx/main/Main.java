package cn.wx.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


import cn.wx.map.CrossRoads;
import cn.wx.map.Point;
import cn.wx.util.Util;

public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<CrossRoads> crossRoads=Util.readMap("src/nodeLink.txt");
		HashSet<Point> points=new HashSet<>();
		ArrayList<CrossRoads> listNew=new ArrayList<>();
		//获取所有点
		for(CrossRoads e:crossRoads) {
			if(!points.contains(e.getPoint())) 
				points.add(e.getPoint());
			for(Point p:e.getPoints()) 
				if(!points.contains(p))
					points.add(p);
		}
		
		File file=new File("allNode.txt");
		FileWriter fileWriter=new FileWriter(file);
		BufferedWriter writer=new BufferedWriter(fileWriter);
		for(Point p:points) {
			String string=""+'('+p.getX()+','+p.getY()+')'+"\t\t\t";
			System.out.println(string);
			writer.write(string+"\n");
		}
		writer.close();
		System.out.println(12);
	}//end Main
}
 