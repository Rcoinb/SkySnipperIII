package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.Menu.ShopMenu;

public class CSMPause extends CSMenu{
	public int select = 1;
	private int sy = 0;
	
	public CSMPause(){
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.drawString("Select size", 25, 50);
		if (select >= 3) select = 3;
		if (select <= 1) select = 1;
		g.drawString("Save ", 25, 350);	
		g.drawString("Continue", 25, 400);	
		g.drawString("Exit", 25, 450);	
		g.drawString(">                      <", 3, sy);
		sy = select * 50 + 300;
	}
	
	public void update(){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		
		if (input.enter.clicked && select == 1) csm.SaveShip();
		if (input.enter.clicked && select == 2 || input.menu.clicked)  csm.setMenu(null);
		if (input.enter.clicked && select == 3) game.setMenu(new ShopMenu());
	}
}
