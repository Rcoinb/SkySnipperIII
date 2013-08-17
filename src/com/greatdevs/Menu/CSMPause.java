package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;

public class CSMPause {
	protected Game game;
	protected InputHandler input;
	private int select = 1;
	private int sy = 400;
	CustomShipMenu csm;
	
	public CSMPause(){
		
	}
	
	public void init(Game game, InputHandler input, CustomShipMenu csm) {
		this.input = input;
		this.game = game;
		this.csm = csm;
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
		if (input.enter.clicked && select == 1 || input.menu.clicked) csm.setMenu(null);;
		if (input.enter.clicked && select == 2) game.setMenu(new ShopMenu());
	}
}
