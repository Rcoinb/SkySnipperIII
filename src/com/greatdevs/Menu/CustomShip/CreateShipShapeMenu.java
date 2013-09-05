package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.Image.ShipIcons;

public class CreateShipShapeMenu extends CSMenu{
	public int select = 0;
	ShipIcons shipicons = new ShipIcons();
	
	ArrayList<ImageComponent> imagecomponents = new ArrayList<ImageComponent>();
	
	ComponentsList componentslist = new ComponentsList();
	
	public CreateShipShapeMenu(){
		componentslist.loadImageComponents(this, shipicons);
		imagecomponents.add(new ImageComponent(shipicons.component[shipicons.component.length - 1], new Rectangle(0, 0, shipicons.component[shipicons.component.length - 1].getWidth(), shipicons.component[shipicons.component.length - 1].getHeight()), 0));
		for (int i = 0; i < imagecomponents.size(); i ++){
			ImageComponent imagecomponent = (ImageComponent) imagecomponents.get(i);
		    imagecomponent.rectangle = new Rectangle(((Game.WIDTH * Game.SCALE) / 2) - (imagecomponent.image.getWidth() / 2), ((Game.HEIGHT * Game.SCALE) / 2) - (imagecomponent.image.getHeight() / 2) + 150, imagecomponent.image.getWidth(), imagecomponent.image.getHeight());
		}
	}
	
	public void render(Graphics g){
		if (select >= shipicons.component.length - 1) select = shipicons.component.length - 1;
		if (select <= 0) select = 0;
		ImageComponent imagecomponent = (ImageComponent) imagecomponents.get(select);
		g.drawImage(imagecomponent.image, imagecomponent.rectangle.x, imagecomponent.rectangle.y,null);
		
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int titlepw = (int) g.getFontMetrics().getStringBounds("Price " + imagecomponent.price, g).getWidth();
		if (imagecomponent.price != 0){
		g.drawString("Price " + imagecomponent.price, (((Game.WIDTH  * Game.SCALE) / 2) - (titlepw / 2)), imagecomponent.rectangle.y - 25);
		}
		
		if (SinglePlayer.COINS < imagecomponent.price){
			g.setColor(Color.RED);
			g.setFont(new Font("Arial", Font.BOLD, 25));
			int title2w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
			g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
	}
	if (SinglePlayer.COINS >= imagecomponent.price){
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int title5w = (int) g.getFontMetrics().getStringBounds("You can buy it", g).getWidth();
		g.drawString("You can buy it", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
    }
	    g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 150));
		String cchc = "<         >";
		int sw = (int) g.getFontMetrics().getStringBounds(cchc, g).getWidth();
		g.drawString(cchc, ((Game.WIDTH * Game.SCALE) / 2) - (sw / 2), 450);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int cstrw = (int) g.getFontMetrics().getStringBounds("Coins " + SinglePlayer.COINS, g).getWidth();
		g.drawString("Coins " + SinglePlayer.COINS, (((Game.WIDTH  * Game.SCALE) / 2) - (cstrw / 2)), 75);
	}
	
	public void update(){
		if (input.left.clicked) select --;
		if (input.right.clicked) select ++;
		if (select >= shipicons.component.length - 1) select = shipicons.component.length - 1;
		if (select <= 0) select = 0;
		ImageComponent imagecomponent = (ImageComponent) imagecomponents.get(select);
			if (input.enter.clicked && SinglePlayer.COINS >= imagecomponent.price && select != (shipicons.component.length - 1) && select != (shipicons.component.length - 2)){
				SinglePlayer.COINS -= imagecomponent.price;
				csm.setMenu(new PlaceComponentMenu(imagecomponent.image));
				imagecomponents.removeAll(imagecomponents);
			}
			else if (input.enter.clicked && select == shipicons.component.length - 1){
				csm.setMenu(null);
			}
			else if (input.enter.clicked && SinglePlayer.COINS >= imagecomponent.price && select == shipicons.component.length - 2){
				SinglePlayer.COINS -= imagecomponent.price;
				csm.setMenu(new DrawComponent());
			}
	}
}
