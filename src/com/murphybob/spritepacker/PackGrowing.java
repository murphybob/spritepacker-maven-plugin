package com.murphybob.spritepacker;

import java.util.ArrayList;

public class PackGrowing {
	
	private Node root;
	private Integer padding = 0;

	/*
	 * Sets free space padding to be created around each packed node
	 * 
	 * @param	padding	padding in pixels
	 */
	public void setPadding( Integer p ){
		padding = p;
	}
	
	/*
	 * Adds Node node property to each element in an array of ImageNodes according to how it could be packed into a larger rectangle
	 * 
	 * @param	images	An ArrayList of ImageNodes to be packed. This should be sorted largest to smallest max side for best results. 
	 */
	public Node fit( ArrayList<ImageNode> images ){
		
		int w = images.get(0).w;
		int h = images.get(0).h;
		
		root = new Node( padding, padding, w, h );
		
		for( ImageNode imageNode: images){
			Node availableNode = findNode( root, imageNode.w, imageNode.h );
			if( availableNode != null ){
				imageNode.node = splitNode( availableNode, imageNode.w, imageNode.h );
			}
			else{
				imageNode.node = growNode( imageNode.w, imageNode.h );
			}
		}
		
		root.w = root.w + padding * 2;
		root.h = root.h + padding * 2;
		
		return root;
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
	
	// Split a node into a node of size w x h and return the remaining space to the pool
	private Node splitNode( Node nodeIn, int w, int h ){
		nodeIn.used = true;
		nodeIn.down = new Node( nodeIn.x, nodeIn.y + h + padding, nodeIn.w, nodeIn.h - h - padding );
		nodeIn.right = new Node( nodeIn.x + w + padding, nodeIn.y, nodeIn.w - w - padding, h );
		return nodeIn;
	}
	
	// Grow the size of the available space to add another block
	private Node growNode( int w, int h ){
		boolean canGrowDown  = (w <= root.w);
		boolean canGrowRight = (h <= root.h);

		boolean shouldGrowRight = canGrowRight && (root.h >= (root.w + w)); // attempt to keep square-ish by growing right when height is much greater than width
		boolean shouldGrowDown  = canGrowDown  && (root.w >= (root.h + h)); // attempt to keep square-ish by growing down  when width  is much greater than height

		if (shouldGrowRight){
			return growRight(w, h);
		}
		else if (shouldGrowDown){
			return growDown(w, h);
		}
		else if (canGrowRight){
			return growRight(w, h);
		}
		else if (canGrowDown){
			return growDown(w, h);
		}
		else {
			return null; // need to ensure sensible root starting size to avoid this happening
		}
	}
	
	// Grow right
	private Node growRight( int w, int h ) {
		Node newRoot = new Node( root.x, root.y, root.w + w + padding, root.h );
		newRoot.used = true;
		newRoot.down = root;
		newRoot.right = new Node( root.w + root.x + padding, root.y, w, root.h );
		root = newRoot;

		Node availableNode = findNode( root, w, h );
		if( availableNode != null ){
			return splitNode( availableNode, w, h );
		}
		else {
			return null;
		}
	}
	
	// Grow down
	private Node growDown( int w, int h ) {
		Node newRoot = new Node( root.x, root.y, root.w, root.h + h + padding );
		newRoot.used = true;
		newRoot.down = new Node( root.x, root.y + root.h + padding, root.w, h );
		newRoot.right = root;
		root = newRoot;

		Node availableNode = findNode( root, w, h );
		if( availableNode != null ){
			return splitNode( availableNode, w, h );
		}
		else {
			return null;
		}
	}
	
}
