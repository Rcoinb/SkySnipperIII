package com.greatdevs.Image;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class  BufferedIL {
    public BufferedImage loadImage(String way){
    	URL url = this.getClass().getResource(way);
    	BufferedImage img = null;
    	
    	try {
			img = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
    }
}