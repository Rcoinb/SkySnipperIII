package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.Entity.ShipTypes;
import com.greatdevs.Menu.ShopMenu;

public class AskSizeMenu extends CSMenu{
	public int select = 1;
	public int price;
	private int sy = 0;
	private int width = 100, height = 100;
	
	public AskSizeMenu(){
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.drawString("Select size", 25, 50);
		if (select >= 4) select = 4;
		if (select <= 1) select = 1;
		g.drawString("Width " + width, 25, 300);
		g.drawString("Height " + height, 25, 350);	
		g.drawString("Create ship", 25, 400);	
		g.drawString("Exit", 25, 450);	
		g.drawString(">                      <", 3, sy);
		sy = select * 50 + 250;
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int titlew = (int) g.getFontMetrics().getStringBounds("Coins " + game.update.gameworld.COINS, g).getWidth();
		g.drawString("Coins " + game.update.gameworld.COINS, (((Game.WIDTH  * Game.SCALE) / 2) - (titlew / 2)), 470);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title2w = (int) g.getFontMetrics().getStringBounds("Price " + price, g).getWidth();
		g.drawString("Price " +price, (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
		
		if (game.update.gameworld.COINS < price){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title3w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
			g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title3w / 2)), 60);
		}
	}
	
	public void update(){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		if (input.enter.clicked && select == 3){
			if (game.update.gameworld.COINS >= price){
			game.update.gameworld.COINS -= price;
			csm.setMenu(null);
			csm.setSize(width, height);
			}
		}
		if (input.enter.clicked && select == 4) game.setMenu(new ShopMenu());
		
		if (input.left.clicked && select == 1) width -= 10;
		if (input.right.clicked && select == 1) width += 10;
		
		if (input.left.clicked && select == 2) height -= 10;
		if (input.right.clicked && select == 2) height += 10;
		
		if (width >= 250) width = 250;
		if (height >= 250) height = 250;
		
		if (width <= 30) width = 30;
		if (height <= 30) height = 30;
		
		price = (((300 - width) + (300 - height)) * 2);
	}
}
