package com.murphybob.spritepacker;

import java.util.ArrayList;

public class PackGrowing {
	
	private Node root;

	/*
	 * Adds Node node property to each element in an array of ImageNodes according to how it could be packed into a larger rectangle
	 * 
	 * @param	images	An ArrayList of ImageNodes to be packed. This should be sorted largest to smallest max side for best results. 
	 */
	public Node fit( ArrayList<ImageNode> images ){
		
		int w = images.get(0).w;
		int h = images.get(0).h;
		
		root = new Node( 0, 0, w, h );
		
		for( ImageNode imageNode: images){
			Node availableNode = findNode( root, imageNode.w, imageNode.h );
			if( availableNode != null ){
				imageNode.node = splitNode( availableNode, imageNode.w, imageNode.h );
			}
			else{
				imageNode.node = growNode( imageNode.w, imageNode.h );
			}
		}
		
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
	
	// Split a node into smaller nodes
	private Node splitNode( Node nodeIn, int w, int h ){
		nodeIn.used = true;
		nodeIn.down = new Node( nodeIn.x, nodeIn.y + h, nodeIn.w, nodeIn.h - h );
		nodeIn.right = new Node( nodeIn.x + w, nodeIn.y, nodeIn.w - w, h );
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
		Node newRoot = new Node( 0, 0, root.w + w, root.h );
		newRoot.used = true;
		newRoot.down = root;
		newRoot.right = new Node( root.w, 0, w, root.h );
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
		Node newRoot = new Node( 0, 0, root.w, root.h + h );
		newRoot.used = true;
		newRoot.down = new Node( 0, root.h, root.w, h );
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

/*

growDown: function(w, h) {
this.root = {
  used: true,
  x: 0,
  y: 0,
  w: this.root.w,
  h: this.root.h + h,
  down:  { x: 0, y: this.root.h, w: this.root.w, h: h },
  right: this.root
};
if (node = this.findNode(this.root, w, h))
  return this.splitNode(node, w, h);
else
  return null;
}


*/