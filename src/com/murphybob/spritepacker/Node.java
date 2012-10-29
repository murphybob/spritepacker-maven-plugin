package com.murphybob.spritepacker;

public class Node {
	public Integer x;
	public Integer y;
	public Integer w;
	public Integer h;
	public Node down;
	public Node right;
	public boolean used = false;
	
	public Node(){}
	
	public Node( int xpos, int ypos ){
		x = xpos;
		y = ypos;
	}
	
	public Node( int xpos, int ypos, int width, int height ){
		x = xpos;
		y = ypos;
		w = width;
		h = height;
	}
	
	public String toString(){
		return "X,Y,W,H,U: " + x + "," + y + "," + w + "," + h + "," + used;// + "\n\tDown X,Y,W,H: " + down.x + "," + down.y + "," + down.w + "," + down.h + "\n\tRight X,Y,W,H: " + right.x + "," + right.y + "," + right.w + "," + right.h;
	}
	
}
