package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Entity.ShipTypes;
import com.greatdevs.GameWorld.GameWorld;
import com.greatdevs.Image.Icons;

public class ShipComponents {
	
	public int sy;
	public int select;
	private int yOffset;
	
	public ArrayList<Component> components = new ArrayList<Component>();
	
	Icons icons = new Icons();
	
	public ShipComponents(){
		components.add(new Component(1, 5, 5, 50000000, icons.player[2], 1));
		components.add(new Component(2, 5, 5, 50, icons.player[3], 2));
		components.add(new Component(3, 5, 5, 50, icons.player[3], 3));
		components.add(new Component(4, 5, 5, 50, icons.player[3], 4));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 5));
		components.add(new Component(6, 5, 5, 50, icons.player[3], 6));
		components.add(new Component(7, 5, 5, 50, icons.player[3], 7));
		components.add(new Component(8, 5, 5, 50, icons.player[3], 8));
		components.add(new Component(9, 5, 5, 50, icons.player[3], 9));
		components.add(new Component(0, 5, 5, 50, icons.player[3], 10));
		components.add(new Component(23, 5, 5, 50, icons.player[4], 11));
		components.add(new Component(4, 5, 5, 50, icons.player[4], 12));
		components.add(new Component(5, 5, 5, 50, icons.player[4], 13));
		components.add(new Component(5, 5, 5, 50, icons.player[4], 14));
		components.add(new Component(5, 5, 5, 50, icons.player[4], 15));
		components.add(new Component(5, 5, 5, 50, icons.player[2], 16));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 17));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 18));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 19));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 20));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 21));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 22));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 23));
		components.add(new Component(5, 5, 5, 50, icons.player[2], 24));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 25));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 26));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 27));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 28));
		components.add(new Component(5, 5, 5, 50, icons.player[3], 29));
		components.add(new Component(5, 5, 5, 50, icons.player[0], 30));
	}
	
	public Rectangle getSelectRect(){
		return new Rectangle(0, sy, 50, 50);
	}
	
	public void render(Graphics g, Game game, CustomShipMenu csm){
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		
		if (select <= -1){
			if (yOffset != 0){
			yOffset -= 50;
			}
			select = 0;
		}
		if (select >= 10){
			if (yOffset < (components.size() * 50 - 500)){
			yOffset += 50;
			}
			select = 9;
		}
		sy = select * 50;
		
		for(int i = 0; i < components.size(); i ++){
			Component component = (Component) components.get(i);
			component.setComponentRect(0, i * 50 - yOffset, 50, 50);
			Rectangle componentrect = component.rect;
			g.drawImage(component.image, componentrect.x, componentrect.y, componentrect.width, componentrect.height, null);
			if (component.isSelected(getSelectRect())){
				g.setFont(new Font("Arial", Font.BOLD, 10));
				int cy = component.rect.y;
				g.setColor(Color.BLUE);
				g.fillRoundRect(50, cy, 150, 50, 25, 25);
				g.setColor(Color.BLACK);
				g.fillRoundRect(55, cy + 5, 140, 40, 25, 25);
				g.setColor(Color.WHITE);
				g.drawString("HP +" + component.getComponentStats()[0], 60, cy + 20);
				g.drawString("Speed +" + component.getComponentStats()[1], 110, cy + 20);
				g.drawString("Reload speed +" + component.getComponentStats()[2], 60, cy + 30);
				g.drawString("Price " + component.getComponentStats()[3], 60, cy + 40);
				
				
				
				if (game.update.gameworld.COINS < component.getComponentStats()[3]){
						g.setColor(Color.RED);
						g.setFont(new Font("Arial", Font.BOLD, 25));
						int title2w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
						g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
				}
				if (game.update.gameworld.COINS >= component.getComponentStats()[3]){
					g.setColor(Color.YELLOW);
					g.setFont(new Font("Arial", Font.BOLD, 25));
					int title5w = (int) g.getFontMetrics().getStringBounds("You can buy it", g).getWidth();
					g.drawString("You can buy it", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
			    }
			}
		}
		
		g.setColor(Color.RED);
		g.drawRect(getSelectRect().x, getSelectRect().y, getSelectRect().width, getSelectRect().height);
	}
	
	public void update(Game game, InputHandler input, CustomShipMenu csm){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		for(int i = 0; i < components.size(); i ++){
			Component component = (Component) components.get(i);
			if (component.isSelected(getSelectRect())){
				if (input.enter.clicked && game.update.gameworld.COINS >= component.getComponentStats()[3]){
					game.update.gameworld.COINS -= component.getComponentStats()[3];
					csm.addComponentsStats(component.getComponentStats());
				}
			}
		}
	}
}
