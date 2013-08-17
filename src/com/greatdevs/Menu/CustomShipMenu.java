package com.greatdevs.Menu;

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

public class CustomShipMenu extends Menu{
	public int x, y, width = 150, height = 150;
	public boolean MENU = false;
	public String name = "name";
	private double backgroundx = 0;
	CSMPause menu;
	
	public ArrayList<BufferedImage> components = new ArrayList<BufferedImage>();
	public ArrayList<Dimension> componentsdimensions = new ArrayList<Dimension>();
	public ArrayList<Color> componentscolors = new ArrayList<Color>();
	 
	public BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	
	public CustomShipMenu(){
		x = (((Game.WIDTH * Game.SCALE) / 2) - (width / 2));
		y = (((Game.HEIGHT * Game.SCALE) / 2) - (height / 2));
		
		addComponent(new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB), new Dimension(50, 50), new Rectangle(0,0,50,50));
		addComponent(new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB), new Dimension(0, 0), new Rectangle(0,0,50,50));
		addComponent(new BufferedImage(30, 30, BufferedImage.TYPE_INT_RGB), new Dimension(100, 100), new Rectangle(0,0,50,50));
	}
	
	public void setMenu(CSMPause menu) {
		this.menu = menu;
		if (menu != null) menu.init(game, input, this);
		if (menu != null) MENU = true;
		if (menu == null) MENU = false;
	}
	
	public void render(Graphics g){
		BackGroundrender(g);
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
		renderframe(g);
		g.drawImage(image, x, y, null);
		if (MENU){
			menu.render(g);
		}
	}
	
	public void renderShip(){
		Graphics g = image.getGraphics();
		g.drawImage(game.shipicons.bg, x, y, width, height, null);
		for (BufferedImage bufferedimage : components){
			for (Dimension dimension : componentsdimensions){
		        g.drawImage(bufferedimage, (int) dimension.getWidth(), (int) dimension.getHeight(), null);
			}
		}
		g.dispose();
	}
	
	public void addComponent(BufferedImage bufferedimage, Dimension dimension, Rectangle rectangle){
		Graphics g = bufferedimage.getGraphics();
		g.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
		componentsdimensions.add(dimension);
		components.add(bufferedimage);
	}
	
	public void update(){
		if (!MENU){
		backgroundx += 0.5;
		if (input.menu.clicked) setMenu(new CSMPause());
		if (input.enter.clicked){
			try {
			    File outputfile = new File("C://Users//Public//SkySnipperIII//" + name + ".png");
			    ImageIO.write(image, "png", outputfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		} else if (MENU){
			menu.update();
		}
		renderShip();
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
