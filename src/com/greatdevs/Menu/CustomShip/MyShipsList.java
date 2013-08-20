package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.greatdevs.Game;
import com.greatdevs.Entity.ShipTypes;
import com.greatdevs.Menu.DrawStats;
import com.greatdevs.Menu.Menu;
import com.greatdevs.Menu.ShopMenu;

public class MyShipsList extends Menu{
	double backgroundx;
	public int sy, yOffset, currectship;
	public int select;
	
	DrawStats drawstats = new DrawStats();
	
	ArrayList<ship> ships = new ArrayList<ship>();
	
	public MyShipsList(){
		try {
			readlist();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void readlist() throws Exception{
		try{ 
        String inputLine;      
        BufferedReader reader = new BufferedReader(new FileReader(new File("C://Users//Public//SkySnipperIII//Ships//list.txt")));
     //   BufferedReader reader = new BufferedReader(new FileReader(new File("C://Users//Public//SkySnipperIII//Ships//list.txt")));
        inputLine = reader.readLine();          
       while (null != (inputLine = reader.readLine()))   
       {  
    	   ships.add(new ship(inputLine));
       }  
       reader.close();
	    }
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void render(Graphics g){
		if (select <= -1 && yOffset > 0){
			select = 0;
			yOffset -= 50;
		}
		if (select <= 0){
			select = 0;
		}
		if (select >= 10){
			if (yOffset < (ships.size() * 50 - 500)){
			yOffset += 50;
			}
			select = 9;
		}
		
		if (yOffset == 0 && select >= ships.size() - 1){
			select = ships.size() - 1;
		}
		
		if (currectship <= 0){
			currectship = 0;
		}
		if (currectship >= ships.size() - 1){
			currectship = ships.size() - 1;
		}
		
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		
		for (int i = 0; i < ships.size(); i ++){
			ship shipclass = (ship) ships.get(i);
			g.drawString(shipclass.name, 25, i * 50 + 50 - yOffset);
		}
		
		ship shipclass = (ship) ships.get(currectship);
		g.drawImage(shipclass.image, ((Game.WIDTH * Game.SCALE) / 2) - (shipclass.image.getWidth() / 2), ((Game.HEIGHT * Game.SCALE) / 2) - (shipclass.image.getHeight() / 2), null);
		
 		renderstats(g, game, shipclass.type, shipclass.image, shipclass.name);   
		
		sy = select * 50 + 50;
		
		g.drawString(">                     <", 3, sy);
	}
	
	public void renderstats(Graphics g, Game game, int[] type, BufferedImage image, String name){
		g.setColor(new Color(0,0,0,225));
 		g.fillRect(((Game.WIDTH * Game.SCALE) - ((Game.WIDTH * Game.SCALE) / 4)), 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
 		g.setColor(Color.WHITE);
 		g.setFont(new Font("Arial", Font.BOLD, 25));
 		
		int hp = type[0];
		int speed = type[1];
		int reloadspeed = type[2]; 		
 		g.drawString("Name: " + name, 775, 50);
 		g.drawString("HP: " + hp, 775, 150);
 		g.drawString("Speed: " + speed, 775, 200);
 		g.drawString("Reload speed: " + reloadspeed, 775, 250);
 		int width = image.getWidth();
 		int heigh = image.getHeight();
 		g.drawString("Width: " + width, 775, 300);
 		g.drawString("Height: " + heigh, 775, 350);
 		if (type.equals(ShipTypes.type2)) g.setFont(new Font("Arial", Font.BOLD, 17));
 		else g.setFont(new Font("Arial", Font.BOLD, 25));
 		g.drawString("*Random", 775, 400);
 		g.setFont(new Font("Arial", Font.BOLD, 25));
	}
	
	public void update(){
		backgroundx += 0.5;
		if (input.up.clicked){
			select --;
			currectship --;
		}
		if (input.down.clicked){
			select ++;
			currectship ++;
		}
		
		if (input.enter.clicked){
			ship shipclass = (ship) ships.get(currectship);
			ShipTypes.setOwnType(shipclass.type, shipclass.image, shipclass.name);
			game.setMenu(new ShopMenu());
		}
		
		if (input.menu.clicked){
			game.setMenu(new FirstMenu());
		}
	}
	
	class ship{
		public String name;
		public int[] type = new int[4];
		public BufferedImage image;
		
		public ship(String name){
			this.name = name;
			try {
				loadship(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void loadship(String name) throws Exception{
		        BufferedReader saveFile =  new BufferedReader(new FileReader(new File("C://Users//Public//SkySnipperIII//Ships//" + name + ".txt")));
				saveFile.readLine(); 
			    type[0] = Integer.parseInt(saveFile.readLine());
			    type[1] = Integer.parseInt(saveFile.readLine());
			    type[2] = Integer.parseInt(saveFile.readLine());
			    type[3] = Integer.parseInt(saveFile.readLine());
			    
			    image = ImageIO.read(new File("C://Users//Public//SkySnipperIII//Ships//" + name + ".png"));
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
