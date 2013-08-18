package com.greatdevs.Menu.CustomShip;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Component {
	public int hp, maxspeed, reloadtime, price, id;
	public Rectangle rect;
	public boolean selected = false;
	public BufferedImage image = null;
	
	public Component(int hp, int maxspeed, int reloadtime, int price, BufferedImage image, int id){
		this.hp = hp;
		this.maxspeed = maxspeed;
		this.reloadtime = reloadtime;
		this.price = price;
		this.image = image;
		this.id = id;
	}
	
	public void setComponentRect(int x, int y, int width, int height){
		this.rect = new Rectangle(x, y, width, height);
	}
	
	public boolean isSelected(Rectangle rect){
		if (this.rect.intersects(rect)){
			return true;
		}
		return false;
	}
	
	public int[] getComponentStats(){
		return new int[]{hp, maxspeed, reloadtime, price};
	}
	
	public int getID(){
		return id;
	}
}
