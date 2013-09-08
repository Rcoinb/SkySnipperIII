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
	
	public int timer, timerend = 400, logotype;
	BufferedImage logo;
	
	public boolean restore = false;
	
	public double backgroundx, size = 1;
	
	public GDMenu(){
		logo = loadImage("/gdlogo.png");
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		
		g.drawImage(logo, (Game.WIDTH * Game.SCALE) / 2 - (int) (logo.getWidth() * size) / 2, (Game.HEIGHT * Game.SCALE) / 2 - (int) (logo.getHeight() * size) / 2, (int) (logo.getWidth() * size), (int) (logo.getHeight() * size), null);
		
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(Color.WHITE);
		String musicinfo = "Music: Revolution (by Reactor) mix.";
		int titlew = (int) g.getFontMetrics().getStringBounds(musicinfo, g).getWidth();
		int titleh = (int) g.getFontMetrics().getStringBounds(musicinfo, g).getHeight();
		g.drawString(musicinfo, (Game.WIDTH * Game.SCALE) - titlew - 10, (Game.HEIGHT * Game.SCALE) - titleh + 5);
	}
	
	public void update(){
		backgroundx += 0.5;
		timer ++;
		
		if (timer >= timerend / 2){
			logo = loadImage("/orangeablogo.png");
		}
		
		size -= 0.0025;
		
		if (size <= 0){
			size = 0;
		}
		
		if (timer >= timerend){
			game.setMenu(new MainMenu());
		}
		if (input.menu.clicked || input.enter.clicked || input.attack.clicked) game.setMenu(new MainMenu());
		if (input.attack.clicked) Sound.play("button.wav");
	}
	
	public void BackGroundrender(Graphics g){
		if (backgroundx > 1000){
			backgroundx = 0;
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(game.icons.background, 250 * w - (int) backgroundx, 250 * h, null);
			}
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(game.icons.background, 250 * w - (int) backgroundx + 1000, 250 * h, null);
			}
		}
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
