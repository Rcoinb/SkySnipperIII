package com.greatdevs.Entity.SuperPower;

import java.awt.Graphics;

import com.greatdevs.Game;

public class P3Power extends SuperPower{
	
	public void render(Graphics g){
		
	}
	
	public void update(Game game){
		player.stopsuperpower();
	}
}
