package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import com.greatdevs.Game;
import com.greatdevs.Sound.Sound;

public class GDMenu extends Menu{
	
	public int timer, timerend = 100;
	BufferedImage image;
	
	public GDMenu(){
		image = loadImage("/gdlogo.png");
	}
	
	public void render(Graphics g){
		g.drawImage(image, 0, 0, null);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.WHITE);
		String musicinfo = "Music: Revolution (by Reactor) mix.";
		int titlew = (int) g.getFontMetrics().getStringBounds(musicinfo, g).getWidth();
		int titleh = (int) g.getFontMetrics().getStringBounds(musicinfo, g).getHeight();
		g.drawString(musicinfo, (Game.WIDTH * Game.SCALE) - titlew - 10, (Game.HEIGHT * Game.SCALE) - titleh + 5);
	}
	
	public void update(){
		timer ++;
		if (timer >= timerend){
			game.setMenu(new MainMenu());
		}
		if (input.menu.clicked || input.enter.clicked || input.attack.clicked) game.setMenu(new MainMenu());
		if (input.attack.clicked) Sound.play("button.wav");
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
