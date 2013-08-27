package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;

public class Console{
	protected Game game;
	protected InputHandler input;
	
	public String command = "";
	
	private int timer = 0;
	
	public Console(){

	}
	
	public void init(Game game, InputHandler input) {
		this.input = input;
		this.game = game;
		game.addKeyListener(new KeyAdapter(){
				public void keyTyped(KeyEvent e){
			        char c = e.getKeyChar();
			        if (c != '/' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER && command.length() < 75){ 
			        command += c;
			        }
				}
		});
	}
	
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.GRAY);
		g.fillRect(0, 480, 1000, 20);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", 0, 15));
		g.drawString(">", 10, 495);
		g.drawString(command, 25, 495);
		int commandw = (int) g.getFontMetrics().getStringBounds(command, g).getWidth();
		if (timer % 60 > 30) g.drawString("|",commandw + 23, 494);
		g.setFont(new Font("Arial", Font.BOLD, 25));
	}
	
	public void update(){
		timer ++;
		if (input.backspace.clicked) command = removeChar(command);
		
		if (input.enter.clicked) ConsoleCheckCommand.checkcommand(command, game, this);
		
		if (input.menu.clicked) game.setConsoleMenu(null);
	}
	
	private String removeChar(String string) {
		if (string.length() > 0) string = string.substring(0, string.length() - 1);
		return string;
	}
	
}
