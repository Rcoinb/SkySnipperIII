package com.greatdevs.Menu;

import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;

public class Menu {
	protected Game game;
	protected InputHandler input;

	public void init(Game game, InputHandler input) {
		this.input = input;
		this.game = game;
	}

	public void render(Graphics g) {	
	}
	
	public void update() {
	}
}
