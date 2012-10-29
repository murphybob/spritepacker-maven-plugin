package com.murphybob.spritepacker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Spritepacker {

	public static Integer max_width = 1000;
	public static Integer max_height = 5000;
	public static File output = new File("g:/projects/spritepacker/target/spritesheet.png");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		log("Start");
		
		// Our source files (will come from external when mojo'd)
		File[] imageFiles = {
				new File("g:/projects/spritepacker/src/images/bob.png"),
				new File("g:/projects/spritepacker/src/images/html5.png"),
				new File("g:/projects/spritepacker/src/images/twitterTall.png"),
				new File("g:/projects/spritepacker/src/images/twitter.png"),
				new File("g:/projects/spritepacker/src/images/superman.png")
		};
		
		// Load images into an arraylist of bufferedimages
		ArrayList<ImageNode> images = new ArrayList<ImageNode>();
		for( File f: imageFiles ){
			images.add( new ImageNode(f) );
		}
		
		// Sort by max width / height descending
		Collections.sort(images, new Comparator<ImageNode>() {
			@Override
			public int compare(ImageNode arg0, ImageNode arg1) {
				int max0 = Math.max(arg0.image.getWidth(), arg0.image.getHeight());
				int max1 = Math.max(arg1.image.getWidth(), arg1.image.getHeight());
				return max1 - max0;
			}
		});
		
/*
		for( ImageNode imageNode: images ){
			BufferedImage image = imageNode.image;
			log( imageNode.filename );
			log( "max: " + Math.max(image.getWidth(), image.getHeight()) + ", width: " + image.getWidth() + ", height: " + image.getHeight() );
		}
*/
		
		// Add packing information to the imageNode array
		Pack p = new Pack( max_width, max_height );
		p.fit(images);
		
		// Create the spritesheet and write the images to it according to their allocated nodes
		BufferedImage spritesheet = new BufferedImage( max_width, max_height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gfx = spritesheet.createGraphics();
		for( ImageNode n: images ){
			gfx.drawImage(n.image, n.node.x, n.node.y, n.node.x + n.w, n.node.y + n.h, 0, 0, n.w, n.h, null);
		}

		// Save spritesheet to file
		try {
			ImageIO.write(spritesheet, "png", output);
		} catch (IOException e) {
			log("Couldn't write spritesheet: "+output, e);
		}

	}
	
	static void log(Object message){
		System.out.println(message.toString());
	}

	static void log(Object message, Exception e){
		System.out.println(message.toString() + "\n" + e);
	}

}
