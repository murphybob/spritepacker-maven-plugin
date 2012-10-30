package com.murphybob.spritepacker;

import java.util.ArrayList;

public class Pack {

	public Node root;
	
	// Create a new root node, top left
	public Pack(int w, int h){
		root = new Node( 0, 0, w, h );
	}
	
	// Iterate through the images and try to find a home for each
	public void fit( ArrayList<ImageNode> images ){
		for( ImageNode imageNode: images){
			Node availableNode = findNode( root, imageNode.w, imageNode.h );
			if( availableNode != null ){
				Node finalNode = splitNode( availableNode, imageNode.w, imageNode.h );
				imageNode.node = finalNode;
			}
		}
	}
	
	// Find a place for this node
	private Node findNode( Node nodeIn, int w, int h ){
		if( nodeIn.used == true ){
			Node temp = findNode( nodeIn.right, w, h );
			if( temp != null ){
				nodeIn = temp;
				return nodeIn;
			}
			else{
				return findNode( nodeIn.down, w, h );
			}
		}
		else if( w <= nodeIn.w && h <= nodeIn.h ){
			return nodeIn;
		}
		else{
			return null;
		}
	}
	
	// Split a node into smaller nodes
	private Node splitNode( Node nodeIn, int w, int h ){
		nodeIn.used = true;
		nodeIn.down = new Node( nodeIn.x, nodeIn.y + h, nodeIn.w, nodeIn.h - h );
		nodeIn.right = new Node( nodeIn.x + w, nodeIn.y, nodeIn.w - w, h );
		return nodeIn;
	}	
	
}
