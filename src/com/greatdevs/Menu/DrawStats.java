package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.greatdevs.Game;
import com.greatdevs.Entity.ShipTypes;

public class DrawStats {
	public void render(Graphics g, Game game, int[] type, BufferedImage image, String string){
		g.setColor(new Color(0,0,0,225));
 		g.fillRect(((Game.WIDTH * Game.SCALE) - ((Game.WIDTH * Game.SCALE) / 4)), 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
 		g.setColor(Color.WHITE);
 		g.setFont(new Font("Arial", Font.BOLD, 25));
 		
		int price = type[3];
		int hp = type[0];
		int speed = type[1];
		int reloadspeed = type[2]; 		
 		g.drawString("Price: " + price, 775, 50);
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
		int strw = (int) g.getFontMetrics().getStringBounds("Press enter to buy", g).getWidth();
		g.drawString("Press enter to buy", (Game.WIDTH * Game.SCALE) - strw - 20, 470);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int titlew = (int) g.getFontMetrics().getStringBounds("Coins " + game.update.gameworld.COINS, g).getWidth();
		g.drawString("Coins " + game.update.gameworld.COINS, (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 470);
	
		if (game.update.gameworld.COINS < ShipTypes.getShowType()[3]){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title2w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
			g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
		}
		
		if (ShipTypes.getType().equals(ShipTypes.getShowType())){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title3w = (int) g.getFontMetrics().getStringBounds("Selected", g).getWidth();
			g.drawString("Selected", (((Game.WIDTH  * Game.SCALE) / 2) - (title3w / 2)), 30);

		}
		
		if (ShipTypes.getShowType()[3] == 0 && !ShipTypes.getType().equals(ShipTypes.getShowType())){
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title4w = (int) g.getFontMetrics().getStringBounds("You have it", g).getWidth();
			g.drawString("You have it", (((Game.WIDTH  * Game.SCALE) / 2) - (title4w / 2)), 30);

		}
		
		if (game.update.gameworld.COINS >= ShipTypes.getShowType()[3] && !ShipTypes.getType().equals(ShipTypes.getShowType()) && !(ShipTypes.getShowType()[3] == 0)){
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title5w = (int) g.getFontMetrics().getStringBounds("You can buy it", g).getWidth();
			g.drawString("You can buy it", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);

		}
	}
}
