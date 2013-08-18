package com.greatdevs.Menu.CustomShip;

import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;

public class CSMenu {
	protected Game game;
	protected InputHandler input;
	protected CustomShipMenu csm;

	public void init(Game game, InputHandler input, CustomShipMenu csm) {
		this.input = input;
		this.game = game;
		this.csm = csm;
	}

	public void render(Graphics g) {	
	}
	
	public void update() {
	}
}
