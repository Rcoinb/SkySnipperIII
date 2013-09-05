package com.greatdevs.Menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Game;
import com.greatdevs.StaticGameOptions;
import com.greatdevs.Sound.Sound;

public class OptionsMenu extends Menu{
	private int select = 1;
	private int sy = 0;
	private double backgroundx = 0;
	
	public OptionsMenu(){
		
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		
		g.drawString("Options", 25, 50);
		
		if (StaticGameOptions.PLAY_MUSIC) g.drawString("Music ON", 25, 300);
		else if (!StaticGameOptions.PLAY_MUSIC) g.drawString("Music OFF", 25, 300);
		
		if (StaticGameOptions.PLAY_MUSIC) g.setColor(Color.WHITE);
		else if (!StaticGameOptions.PLAY_MUSIC) g.setColor(Color.GRAY);;
		g.drawString("Volume " + StaticGameOptions.MUSIC_VOLUME, 25, 350);
		
		g.setColor(Color.WHITE);
		
		if (StaticGameOptions.PLAY_SOUNDS) g.drawString("Sounds ON", 25, 400);
		else if (!StaticGameOptions.PLAY_SOUNDS) g.drawString("Sounds OFF", 25, 400);
		g.drawString("Exit", 25, 450);	
		g.drawString(">                         <", 3, sy);
		if (select >= 4) select = 4;
		if (select <= 1) select = 1;
		sy = select * 50 + 250;
	}
	
	public void update(){
		backgroundx += 0.5;
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		
		if (input.enter.clicked && select == 1){
			if (StaticGameOptions.PLAY_MUSIC) StaticGameOptions.PLAY_MUSIC = false;
			else if (!StaticGameOptions.PLAY_MUSIC){
				StaticGameOptions.PLAY_MUSIC = true;
			}
		}
		
		if (select == 2 && StaticGameOptions.PLAY_MUSIC){
			if (input.left.clicked) StaticGameOptions.MUSIC_VOLUME -= 0.5f;
			if (input.right.clicked) StaticGameOptions.MUSIC_VOLUME += 0.5f;
			
			if (StaticGameOptions.MUSIC_VOLUME <= -10) StaticGameOptions.MUSIC_VOLUME = -10;
			if (StaticGameOptions.MUSIC_VOLUME >= 5) StaticGameOptions.MUSIC_VOLUME = 5;
		}
		
		if (input.enter.clicked && select == 3){
			if (StaticGameOptions.PLAY_SOUNDS) StaticGameOptions.PLAY_SOUNDS = false;
			else if (!StaticGameOptions.PLAY_SOUNDS) StaticGameOptions.PLAY_SOUNDS = true;
		}
		
		if (input.enter.clicked && select == 4){ 
			game.setMenu(new MainMenu());
			try {
				game.saveoptions.saveOptions();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Sound.StopMusic();
			Sound.PlayMusic("music.wav", StaticGameOptions.MUSIC_VOLUME);
		}
	}
	
	public void BackGroundrender(Graphics g){
		if (backgroundx > 1000){
			backgroundx = 0;
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(game.icons.background, 250 * w - (int) backgroundx, 250 * h, null);
			}
		}
		for (int h = 0; h < 2; h ++){
			for (int w = 0; w < 4; w ++){
		         g.drawImage(game.icons.background, 250 * w - (int) backgroundx + 1000, 250 * h, null);
			}
		}
	}
}
