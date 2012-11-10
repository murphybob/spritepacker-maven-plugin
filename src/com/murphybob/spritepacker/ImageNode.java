package com.murphybob.spritepacker;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.maven.plugin.MojoExecutionException;

public class ImageNode {
	public File file;
	public BufferedImage image;
	public Node node;
	public int w;
	public int h;
	
	public ImageNode( File f ) throws MojoExecutionException {
		file = f;
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			throw new MojoExecutionException("Failed to open file: " + f.getPath(), e);
		}
		w = image.getWidth();
		h = image.getHeight(); 
	}
	
	public String toString(){
		return file.getPath() + "\n" + node;
	}

}
