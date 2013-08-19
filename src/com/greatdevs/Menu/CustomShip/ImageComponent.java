package com.greatdevs.Menu.CustomShip;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ImageComponent {
	public Rectangle rectangle;
	public boolean selected = false;
	public int price;
	public BufferedImage image = null;
	
	public ImageComponent(BufferedImage image, Rectangle rectangle, int price){
		this.image = image;
		this.rectangle = rectangle;
		this.price = price;
	}
}
