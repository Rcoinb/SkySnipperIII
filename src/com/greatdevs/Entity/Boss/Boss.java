package com.greatdevs.Entity.Boss;

import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.Sound.Sound;

public class Boss {
	public int hp;
	
	public void render(Graphics g){

	}
	
	public void update(Game game){	
		
	}
	
	public void die(Game game){
		Sound.explosion.play();
	}
}
