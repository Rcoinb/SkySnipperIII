package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.Menu.Menu;
import com.greatdevs.Menu.ShopMenu;

public class FirstMenu extends Menu{
	public int select = 1;
	private int sy = 0;
	private double backgroundx = 0;
	
	public FirstMenu(){
		
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.drawString("Custom ships", 25, 50);
		if (select >= 3) select = 3;
		if (select <= 1) select = 1;
		g.drawString("Load ship", 25, 350);	
		g.drawString("Create ship", 25, 400);	
		g.drawString("Exit", 25, 450);	
		g.drawString(">                      <", 3, sy);
		sy = select * 50 + 300;
	}
	
	public void update(){
		backgroundx += 0.5;
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		
		if (input.enter.clicked && select == 1) game.setMenu(new MyShipsList());;
		if (input.enter.clicked && select == 2) game.setMenu(new CustomShipMenu());
		if (input.enter.clicked && select == 3) game.setMenu(new ShopMenu());
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
