package cn.wx.map;

import java.util.HashSet;

public class CrossRoads {
	
	
	private Point point;
	private HashSet<Point> points=new HashSet<>();
	
	public HashSet<Point> getPoints() {
		return points;
	}
	public void setPoints(HashSet<Point> points) {
		this.points = points;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "CrossRoads [point=" + point + ", points=" + points + "]";
	}
}
