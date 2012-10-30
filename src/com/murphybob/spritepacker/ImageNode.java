package com.murphybob.spritepacker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageNode {
	public File filename;
	public BufferedImage image;
	public Node node;
	public int w;
	public int h;
	
	public ImageNode( File f ){
		filename = f;
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			Spritepacker.log("Failed to open file: " + f.getPath(), e);
		}
		w = image.getWidth();
		h = image.getHeight(); 
	}
	
	public String toString(){
		return filename.getPath() + "\n" + node;
	}

}
