package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;
import com.greatdevs.Entity.ShipTypes;
import com.greatdevs.Menu.CustomShip.FirstMenu;

public class ShopMenu extends Menu{
	private int select = 1;
	private int sy = 0;
	private int imagew, imageh;
	private double backgroundx = 0;
	private boolean drawimage = true;
	public BufferedImage image;
	
	public DrawStats drawstats = new DrawStats();
	
	public ShopMenu(){
		
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Shop", 25, 50);
		g.drawString("Player1", 25, 100);
		g.drawString("Player2", 25, 150);	
		g.drawString("Player3", 25, 200);	
		g.drawString("Player4", 25, 250);	
		g.drawString("Player5", 25, 300);	
		g.drawString("Drakula", 25, 350);
		g.drawString("Custom ship", 25, 400);
		g.drawString("Exit", 25, 450);	
		g.drawString(">                        <", 3, sy);
		if (drawimage) g.drawImage(image, 500 - imagew / 2, 250 - imageh / 2, null);
		if (select >= 8) select = 8;
		if (select <= 1) select = 1;
		if (select < 7) {
			image = game.icons.player[(select - 1)]; 
			imagew = image.getWidth();
			imageh = image.getHeight();
			drawimage = true;
			ShipTypes.setShowType(select);
	 		drawstats.render(g, game, ShipTypes.getShowType(), image, ShipTypes.getShowPowerString(), false);   
		}
		if (select == 7){
			if (ShipTypes.ownType){
				image = ShipTypes.playerimage; 
				imagew = image.getWidth();
				imageh = image.getHeight();
				g.drawImage(image, 500 - imagew / 2, 250 - imageh / 2, null);
				drawstats.render(g, game, ShipTypes.getType(), image, "Random", true);   
			}
		}
		if (select == 7) drawimage = false;
		sy = select * 50 + 50;
	}
	
	public void update(){
		backgroundx += 0.5;
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		if (input.enter.clicked && select != 7 && select != 8){
			if (game.update.gameworld.COINS >= ShipTypes.getShowType()[3]){
			ShipTypes.setType(select);
			game.update.gameworld.COINS -= ShipTypes.getShowType()[3];
			ShipTypes.getShowType()[3] = 0;
			}
		}
		if (input.enter.clicked && select == 7) game.setMenu(new FirstMenu());
		if (input.enter.clicked && select == 8) game.setMenu(new MainMenu());
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
