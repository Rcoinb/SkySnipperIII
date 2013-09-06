package com.greatdevs.GameWorld.Multiplayer.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Menu.Menu;
import com.greatdevs.Menu.SelectGameMode;

public class CreateServer extends Menu{
	public String ip = "", port = "";
	public int select = 1;
	public int sy;
	
	public static boolean error = false;
	
	MultiPlayer mp;
	
	public CreateServer(MultiPlayer mp){
		this.mp = mp;
		error = false;
	}
	
	public void init(Game game, InputHandler input) {
		this.input = input;
		this.game = game;
		
		game.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
		        char c = e.getKeyChar();
		        if (c != '/' && c != KeyEvent.VK_BACK_SPACE && c != KeyEvent.VK_ENTER && ip.length() < 17){ 
		        	if (select == 1)
		        ip += c;
		        	else if (select == 2)
		        port += c;
		        }
			}
		});
	}
	
	public static void ERROR(){
		error = true;
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE), Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 3, Game.HEIGHT * Game.SCALE);
		if (select >= 4) select = 4;
		if (select <= 1) select = 1;
		sy = select * 50 + 250;
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Create server", 25, 50);
		g.drawString("IP " + ip, 25, 300);
		g.drawString("Port " + port, 25, 350);
		g.drawString("Create server", 25, 400);
		g.drawString("Exit", 25, 450);
		g.drawString(">                                        <", 3, sy);
		
		if (error){
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title2w = (int) g.getFontMetrics().getStringBounds("Oops.. Something went wrong..", g).getWidth();
		g.drawString("Oops.. Something went wrong..", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
		}
	}

	private void connect(){
		mp.runServer(ip, Integer.parseInt(port));
	}
	
	private String removeChar(String string) {
		if (string.length() > 0) string = string.substring(0, string.length() - 1);
		return string;
	}
	
	public void update(){
		if (input.arrowup.clicked) select --;
		if (input.arrowdown.clicked) select ++;
		
		if (select == 1){
			if (input.backspace.clicked) ip = removeChar(ip);
			if (input.enter.clicked || input.menu.clicked){
				
			}
		}
		if (select == 2){
			if (input.backspace.clicked) port = removeChar(port);
			if (input.enter.clicked || input.menu.clicked){
				
			}
		}
		if (input.enter.clicked && select == 3 && !ip.equals("")) connect();
		else if (ip.equals("")) select = 1;
		if (input.enter.clicked && select == 4) game.setMenu(new SelectGameMode());
	}
}
