package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;

import com.greatdevs.Game;

public class LoadingMenu extends Menu{
	
	private int endtime, timer;
	private double backgroundx = 0;
	
	ImageIcon imageIcon = new ImageIcon(LoadingMenu.class.getResource("/loading.gif"));
	
	public LoadingMenu(int endtime){
		this.endtime = endtime;
	}
	
	public void render(Graphics g) {
		BackGroundrender(g);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 100));
		int titlew = (int) g.getFontMetrics().getStringBounds("Sky Snipper III", g).getWidth();
		g.drawString("Sky Snipper III", (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 150);
		g.drawImage(imageIcon.getImage(), ((Game.WIDTH * Game.SCALE) / 2) - (512 / 2), 275, null);
	}
	
	public void update() {
		backgroundx += 0.5;
		timer ++;
		if (timer >= endtime){
			game.setMenu(null);
		}
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
}
