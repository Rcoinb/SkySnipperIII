package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;

public class LoseMenu extends Menu{
	
	public LoseMenu(){
		
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Game Over", 25, 50);
		g.drawString("Score " + game.update.gameworld.SCORE, 25, 400);
		g.drawString("Exit", 25, 435);	
		g.drawString(">                 <", 3, 435);
	}
	
	public void update(){
		if (input.enter.clicked) game.setMenu(new MainMenu());
	}
}
