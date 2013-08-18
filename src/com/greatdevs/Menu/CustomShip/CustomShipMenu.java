package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Menu.Menu;

public class CustomShipMenu extends Menu{
	public int x, y, width, height;
	public boolean MENU = false;
	public String name = "name";
	private double backgroundx = 0;
	public CSMenu menu;
	
	public DrawCSStats drawstats = new DrawCSStats();
	public ShipComponents shipcomponents = new ShipComponents();
	
	public ArrayList<BufferedImage> components = new ArrayList<BufferedImage>();
	public ArrayList<Dimension> componentsdimensions = new ArrayList<Dimension>();
	public ArrayList<Color> componentscolors = new ArrayList<Color>();
	 
	//HP, maxspeed, reloadtime, price
	public static final int[] shiptype = {5, 5, 25, 0};
	
	public BufferedImage shipimage;
	
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
		
		addShipComponent(new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB), new Dimension(50, 50), new Rectangle(0,0,50,50));
		addShipComponent(new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB), new Dimension(0, 0), new Rectangle(0,0,50,50));
		addShipComponent(new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB), new Dimension(100, 100), new Rectangle(0,0,50,50));
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
		drawstats.render(g, game, shiptype, shipimage, "Something");
		shipcomponents.render(g, game, this);
		if (MENU){
			menu.render(g);
		}
	}
	
	public void renderShip(){
		Graphics g = shipimage.getGraphics();
		g.drawImage(game.shipicons.bg, x, y, width, height, null);
		for (BufferedImage bufferedimage : components){
			for (Dimension dimension : componentsdimensions){
		        g.drawImage(bufferedimage, (int) dimension.getWidth(), (int) dimension.getHeight(), null);
			}
		}
		g.dispose();
	}
	
	public void addShipComponent(BufferedImage bufferedimage, Dimension dimension, Rectangle rectangle){
		Graphics g = bufferedimage.getGraphics();
		g.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
		componentsdimensions.add(dimension);
		components.add(bufferedimage);
	}
	public void addComponentsStats(int[] component){
		shiptype[0] += component[0];
		shiptype[1] += component[1];
		shiptype[2] += component[2];
	}
	
	public void SaveShip(){
			try {
			    File outputfile = new File("C://Users//Public//SkySnipperIII//" + name + ".png");
			    ImageIO.write(shipimage, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void update(){
		if (!MENU){
		backgroundx += 0.5;
		renderShip();
		shipcomponents.update(game, input, this);
		if (input.menu.clicked) setMenu(new CSMPause());
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
		g.fillRect(x - 5, y - 5, width + 10, height + 10);
		g.setColor(Color.RED);
		g.drawRect(x - 1, y - 1, width + 2, height + 2);
	}
}
