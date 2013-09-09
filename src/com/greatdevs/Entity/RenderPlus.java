package com.greatdevs.Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;

public class RenderPlus {
	public int x, y, timer = 0, color = 255;
	public String str;
	public boolean removed = false;

	public RenderPlus(String str, int x, int y) {
		this.str = str;
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 15));
		g.setColor(new Color(255, 255, 255, color));
		g.drawString(str, x, y);
	}

	public void update(Game game) {
		timer++;
		y -= 2;
		if (color > 0) {
			color -= 5;
		}
		if (timer >= 25){
			removed = true;
		}
	}
}
