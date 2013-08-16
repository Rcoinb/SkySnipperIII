package com.greatdevs.GameUpdate;

import java.awt.Graphics;

public class Render{
	public void render(Graphics g, Update update){
		update.gameworld.render(g);
		update.entity.render(g);
	}
}
