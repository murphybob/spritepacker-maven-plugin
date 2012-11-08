package com.murphybob.spritepacker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Spritepacker {
	
	private static long startTime = System.currentTimeMillis();
	
	/*
	 * These will come from pom when mojo'd
	 */
	private static File output = new File("target/spritesheet.png");
	private static File[] imageFiles = {
		new File("src/images/bob.png"),
		new File("src/images/html5.png"),
		new File("src/images/twitterTall.png"),
		new File("src/images/twitter.png"),
		new File("src/images/superman.png"),
		new File("src/images/bob.png"),
		new File("src/images/twitterTall.png"),
		new File("src/images/twitter.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/twsmall.png"),
		new File("src/images/superman.png")
	};
	private static Integer padding = 50;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		log("Started Spritepacker.");
		
		log("Loading " + imageFiles.length + " images...");
		
		// Load images defined in input array
		ArrayList<ImageNode> images = loadImages( imageFiles );

		log("Sorting images...");
		
		// Sort images to be largest (by max(width,height)) first
		sortImages( images );		
		
		log("Packing images...");
		
		// Add packing information
		Node root = packImages( images );
		
		log("Saving spritesheet...");
		
		// Put to a spritesheet and write to a file
		saveSpritesheet( images, root, output );
		
		float took = secondsSinceStart();
		log("Done - took " + took + "s!");

	}
	
	private static ArrayList<ImageNode> loadImages( File[] imageFiles ){
		ArrayList<ImageNode> images = new ArrayList<ImageNode>();
		for( File f: imageFiles ){
			images.add( new ImageNode(f) );
		}
		return images;
	}
	
	private static void sortImages( ArrayList<ImageNode> images ){
		// Sort by max width / height descending
		Collections.sort(images, new Comparator<ImageNode>() {
			@Override
			public int compare(ImageNode arg0, ImageNode arg1) {
				int max0 = Math.max(arg0.image.getWidth(), arg0.image.getHeight());
				int max1 = Math.max(arg1.image.getWidth(), arg1.image.getHeight());
				return max1 - max0;
			}
		});
	}
	
	private static Node packImages( ArrayList<ImageNode> images ){
		PackGrowing p = new PackGrowing();
		p.setPadding( padding );
		return p.fit(images);		
	}
	
	private static void saveSpritesheet( ArrayList<ImageNode> images, Node root, File output ){
		BufferedImage spritesheet = new BufferedImage( root.w, root.h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D gfx = spritesheet.createGraphics();
		for( ImageNode n: images ){
			gfx.drawImage(n.image, n.node.x, n.node.y, n.node.x + n.w, n.node.y + n.h, 0, 0, n.w, n.h, null);
		}

		if (!output.getParentFile().exists() && !output.getParentFile().mkdirs()) {
			log("Couldn't create target directory", new Exception("Cannot create output directory " + output.getParentFile()));
		}
		
		try {
			ImageIO.write(spritesheet, "png", output);
		} catch (IOException e) {
			log("Couldn't write spritesheet: "+output, e);
		}
		
	}
	
	static float secondsSinceStart(){
		float tookM = (System.currentTimeMillis() - startTime);
		return tookM / 1000;
	}
	
	static void log(Object message){
		writeConsoleMessage( message.toString() );
	}

	static void log(Object message, Exception e){
		writeConsoleMessage( message.toString() + "\n" + e );
	}
	
	static void writeConsoleMessage( String message ){
		String t = new SimpleDateFormat( "dd/MM/yy hh:mm:ss").format( System.currentTimeMillis() );
		System.out.println("[" + t + "] " + message.toString());
	}

}
