package com.greatdevs.GameWorld;

import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;

public class GameMode {
	public Game game;
	public InputHandler input;
	
	public int objectspeed;
	
	public static int GAMEMODE;
	
	public void init(Game game, InputHandler input){
		this.game = game;
		this.input = input;
	}
	
	public void render(Graphics g){
		
	}
	
	public void update(){
		
	}
	
	public void speeddown(){
		
	}
}
