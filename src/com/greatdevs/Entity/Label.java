package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;

public class Label {	
	public String text;
	public boolean finished = false;
	private double animation;
	
	public BufferedImage image;
	
	public Label(String text){
		this.text = text;
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, (int) animation));
		int strw = (int) g.getFontMetrics().getStringBounds(text, g).getWidth();
	    int strh = (int) g.getFontMetrics().getStringBounds(text, g).getHeight();
		g.drawString(text, 500 - (strw / 2), 250 - (strh / 2));
	}
	
	public void update(Game game){	
		animation += 2.5;
		
		if (animation > 100){
			finished = true;
		}
		
		image = game.icons.coin[2];
	}
}
