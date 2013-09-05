package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.GameWorld.SinglePlayer;
import com.greatdevs.Image.Icons;
import com.greatdevs.Image.ShipIcons;

public class ShipComponents {
	
	public int sy;
	public int select;

	private int yOffset;
	
	public ArrayList<Component> components = new ArrayList<Component>();
	
	ComponentsList componentslist = new ComponentsList();
	Icons icons = new Icons();
	ShipIcons shipicons = new ShipIcons();
	
	public ShipComponents(){
		componentslist.loadStatsComponents(this, shipicons);
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
			if (component.used) g.drawImage(shipicons.used, componentrect.x, componentrect.y, componentrect.width, componentrect.height, null);
			if (component.isSelected(getSelectRect())){
				g.setFont(new Font("Arial", Font.BOLD, 10));
				int cy = component.rect.y;
				if (component.id != 0){
				g.setColor(Color.BLUE);
				g.fillRoundRect(50, cy, 150, 50, 25, 25);
				g.setColor(Color.BLACK);
				g.fillRoundRect(55, cy + 5, 140, 40, 25, 25);
				g.setColor(Color.WHITE);
				if (component.getComponentStats()[0] >= 0) g.drawString("HP +" + component.getComponentStats()[0], 60, cy + 20);
				else g.drawString("HP " + component.getComponentStats()[0], 60, cy + 20);
				
				if (component.getComponentStats()[1] >= 0) g.drawString("Speed +" + component.getComponentStats()[1], 110, cy + 20);
				else g.drawString("Speed " + component.getComponentStats()[1], 110, cy + 20);
				
				if (component.getComponentStats()[2] >= 0) g.drawString("Reload speed +" + component.getComponentStats()[2], 60, cy + 30);
				else g.drawString("Reload speed " + component.getComponentStats()[2], 60, cy + 30);
				
				g.drawString("Price " + component.getComponentStats()[3], 60, cy + 40);
				}
				
				
				
				if (SinglePlayer.COINS < component.getComponentStats()[3]){
						g.setColor(Color.RED);
						g.setFont(new Font("Arial", Font.BOLD, 25));
						int title2w = (int) g.getFontMetrics().getStringBounds("No money", g).getWidth();
						g.drawString("No money", (((Game.WIDTH  * Game.SCALE) / 2) - (title2w / 2)), 30);
				}
				if (SinglePlayer.COINS >= component.getComponentStats()[3] && !component.used){
					g.setColor(Color.YELLOW);
					g.setFont(new Font("Arial", Font.BOLD, 25));
					int title5w = (int) g.getFontMetrics().getStringBounds("You can buy it", g).getWidth();
					g.drawString("You can buy it", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
			    }
				else if (component.used){
					g.setColor(Color.WHITE);
					g.setFont(new Font("Arial", Font.BOLD, 25));
					int title5w = (int) g.getFontMetrics().getStringBounds("You can buy it", g).getWidth();
					g.drawString("You have it", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
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
				if (component.getID() != 0){
				if (input.enter.clicked && SinglePlayer.COINS >= component.getComponentStats()[3] && !component.used){
					SinglePlayer.COINS -= component.getComponentStats()[3];
					csm.addComponentsStats(component.getComponentStats());
					if (csm.shiptype[0] <= 1) csm.shiptype[0] = 1;
					if (csm.shiptype[1] <= 1) csm.shiptype[1] = 1;
					if (csm.shiptype[2] <= 1) csm.shiptype[2] = 1;
					
					component.used = true;
				}
				}
				else if (component.getID() == 0){
					if (input.enter.clicked){
						csm.setMenu(new SaveShipMenu());;
					}
				}
			}
		}
	}
}
