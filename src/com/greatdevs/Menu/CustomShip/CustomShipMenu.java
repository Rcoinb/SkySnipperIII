package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Menu.Menu;

public class CustomShipMenu extends Menu{
	public int x, y, width = 2, height = 2;
	public boolean MENU = false;
	private double backgroundx = 0;
	public CSMenu menu;
	
	public DrawCSStats drawstats = new DrawCSStats();
	public ShipComponents shipcomponents = new ShipComponents();
	 
	public ArrayList<component> componentsarray = new ArrayList<component>();
	
	//HP, maxspeed, reloadtime, price
	public int[] shiptype = {5, 5, 25, 0};
	
	public BufferedImage shipimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);;
	
	public CustomShipMenu(){
		
	}
	
	public void init(Game game, InputHandler input) {
		this.input = input;
		this.game = game;
		setMenu(new AskSizeMenu());
	}
	
	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
		
		shipimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		x = (((Game.WIDTH * Game.SCALE) / 2) - (width / 2));
		y = (((Game.HEIGHT * Game.SCALE) / 2) - (height / 2));
	}
	
	public void setMenu(CSMenu menu) {
		this.menu = menu;
		if (menu != null) menu.init(game, input, this);
		if (menu != null) MENU = true;
		if (menu == null) MENU = false;
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
 		g.setColor(Color.WHITE);
		renderframe(g);
		g.drawImage(shipimage, x, y, null);
		if (!MENU){
		drawstats.render(g, game, shiptype, shipimage, "Random");
		shipcomponents.render(g, game, this);
		}
		if (MENU){
			menu.render(g);
		}
	}
	
	public void renderShip(){
		Graphics g = shipimage.getGraphics();
		g.drawImage(game.shipicons.bg, x, y, width, height, null);
		for (int i = 0; i < componentsarray.size(); i ++){
			component componentclass = (component) componentsarray.get(i);
		    g.drawImage(componentclass.component, componentclass.componentsrect.x, componentclass.componentsrect.y, componentclass.componentsrect.width, componentclass.componentsrect.height, null);
		}
		g.dispose();
	}
	
	public void addShipComponent(BufferedImage bufferedimage, Rectangle rectangle){
		componentsarray.add(new component(bufferedimage, rectangle));
	}
	
	class component{
		public BufferedImage component;
		public Rectangle componentsrect;
		
		public component(BufferedImage bufferedimage, Rectangle rectangle){
			this.component = bufferedimage;
			this.componentsrect = rectangle;
		}
	}
	
	public void addComponentsStats(int[] component){
		shiptype[0] += component[0];
		shiptype[1] += component[1];
		shiptype[2] += component[2];
	}
	
	public void update(){
		if (!MENU){
		backgroundx += 0.5;
		renderShip();
		shipcomponents.update(game, input, this);
		} else if (MENU){
			menu.update();
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
	
	public void renderframe(Graphics g){
		g.setColor(Color.BLUE);
		g.drawRect(x - 2, y - 2, width + 4, height + 4);
		g.setColor(Color.RED);
		g.drawRect(x - 1, y - 1, width + 2, height + 2);
	}
}
