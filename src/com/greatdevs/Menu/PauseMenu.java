package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Graphics;

import com.greatdevs.Game;

public class PauseMenu extends Menu{

	private int select = 1;
	private int sy = 400;
	
	public PauseMenu(){
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.drawString("Pause", 25, 50);
		if (select >= 2) select = 2;
		if (select <= 1) select = 1;
		g.drawString("Continue", 25, 400);	
		g.drawString("Exit", 25, 450);	
		g.drawString(">                      <", 3, sy);
		sy = select * 50 + 350;
	}
	
	public void update(){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		if (input.menu.clicked) game.setMenu(null);
		
		if (input.enter.clicked && select == 1) game.setMenu(null);
		if (input.enter.clicked && select == 2){
			game.update.entity.removeAllArrays();
			game.setMenu(new MainMenu());
		}
	}
}
