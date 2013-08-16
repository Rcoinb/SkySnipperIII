package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
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
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Pause", 25, 50);
		g.drawString("Continue", 25, 400);
		g.drawString("Exit", 25, 435);	
		g.drawString(">                 <", 3, sy);
	}
	
	public void update(){
		if (input.up.clicked) {select = 1; sy = 400;}
		if (input.down.clicked) {select = 2; sy = 435;}
		if (input.enter.clicked && select == 1) game.setMenu(null);
		if (input.enter.clicked && select == 2) game.setMenu(new MainMenu());
		if (input.menu.clicked) game.setMenu(null);
	}
}
