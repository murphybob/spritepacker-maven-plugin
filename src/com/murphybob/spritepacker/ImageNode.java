package com.murphybob.spritepacker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageNode {
	public File file;
	public BufferedImage image;
	public Node node;
	public int w;
	public int h;
	
	public ImageNode( File f ){
		file = f;
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			Spritepacker.log("Failed to open file: " + f.getPath(), e);
		}
		w = image.getWidth();
		h = image.getHeight(); 
	}
	
	public String toString(){
		return file.getPath() + "\n" + node;
	}

}
