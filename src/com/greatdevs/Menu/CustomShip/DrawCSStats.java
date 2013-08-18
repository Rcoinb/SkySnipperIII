package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;
import com.greatdevs.Entity.ShipTypes;

public class DrawCSStats {
	public void render(Graphics g, Game game, int[] type, BufferedImage image, String string){
 		if (image != null){
		g.setColor(new Color(0,0,0,225));
 		g.fillRect(((Game.WIDTH * Game.SCALE) - ((Game.WIDTH * Game.SCALE) / 4)), 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
 		g.setColor(Color.WHITE);
 		g.setFont(new Font("Arial", Font.BOLD, 25));
 		
		int hp = type[0];
		int speed = type[1];
		int reloadspeed = type[2]; 		
 		g.drawString("HP: " + hp, 775, 150);
 		g.drawString("Speed: " + speed, 775, 200);
 		g.drawString("Reload speed: " + reloadspeed, 775, 250);
 		int width = image.getWidth();
 		int heigh = image.getHeight();
 		g.drawString("Width: " + width, 775, 300);
 		g.drawString("Height: " + heigh, 775, 350);
 		if (type.equals(ShipTypes.type2)) g.setFont(new Font("Arial", Font.BOLD, 17));
 		else g.setFont(new Font("Arial", Font.BOLD, 25));
 		g.drawString("*" + string, 775, 400);
 		g.setFont(new Font("Arial", Font.BOLD, 25));
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int titlew = (int) g.getFontMetrics().getStringBounds("Coins " + game.update.gameworld.COINS, g).getWidth();
		g.drawString("Coins " + game.update.gameworld.COINS, (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 470);
 		}
 		else{
 			
 		}
	}
}
