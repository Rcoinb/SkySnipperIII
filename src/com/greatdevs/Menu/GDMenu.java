package com.greatdevs.Menu;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.greatdevs.Sound.Sound;

public class GDMenu extends Menu{
	
	public int timer;
	BufferedImage image;
	
	public GDMenu(){
		image = loadImage("/gdlogo.png");
	}
	
	public void render(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
	
	public void update(){
		timer ++;
		if (timer >= 100){
			game.setMenu(new MainMenu());
		}
		if (input.menu.clicked || input.enter.clicked || input.attack.clicked) game.setMenu(new MainMenu());
		if (input.attack.clicked) Sound.button.play();
	}
	
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
