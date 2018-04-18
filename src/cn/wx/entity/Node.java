package cn.wx.entity;

import cn.wx.map.Point;

public class Node{
	private String name;
	private String password;
	private String token;
	private Point point;
	private Role role;
	private double scope;
	private boolean stable;
	
	public Node(String name, String password, Point point, Role role,double scope,boolean stable) {
		super();
		this.name = name;
		this.password = password;
		this.point = point;
		this.role = role;
		this.scope=scope;
		this.stable=stable;
	}

	public boolean isStable() {
		return stable;
	}
	public void setStable(boolean stable) {
		this.stable = stable;
	}

	public double getScope() {
		return scope;
	}

	public void setScope(double scope) {
		this.scope = scope;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
