package com.greatdevs.Menu.CustomShip;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.greatdevs.Game;
import com.greatdevs.InputHandler;
import com.greatdevs.Image.ShipIcons;

public class PlaceComponentMenu extends CSMenu{
	
	public int select = 1, sy;
	public int x, y, width, height;
	
	ShipIcons shipicons = new ShipIcons();
	
	public BufferedImage image, drawimage;
	public BufferedImage shipimageex;
	public Rectangle rectangle;
	
	public PlaceComponentMenu(BufferedImage image){
		this.image = image;
		drawimage = image;
		width = image.getWidth();
		height = image.getHeight();
		rectangle = new Rectangle(x, y, width, height);
	}
	
	public void init(Game game, InputHandler input, CustomShipMenu csm) {
		this.input = input;
		this.game = game;
		this.csm = csm;
		shipimageex = new BufferedImage(csm.width, csm.height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void render(Graphics g){
		g.setColor(new Color(0,0,0,200));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(new Color(0,0,0,225));
		g.fillRect(0, 0, (Game.WIDTH * Game.SCALE) / 4, Game.HEIGHT * Game.SCALE);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.drawString("Place component", 25, 50);
		if (select >= 5) select = 5;
		if (select <= 1) select = 1;
		g.drawString("X pos " + x, 25, 250);
		g.drawString("Y pos " + y, 25, 300);	
		g.drawString("Width " + width, 25, 350);
		g.drawString("Height " + height, 25, 400);	
		g.drawString("Place", 25, 450);		
		g.drawString(">                      <", 3, sy);
		
		renderimage();
		g.drawImage(shipimageex, (((Game.WIDTH * Game.SCALE) / 2) - (shipimageex.getWidth() / 2)), (((Game.HEIGHT * Game.SCALE) / 2) - (shipimageex.getHeight() / 2)), null);
		
		sy = select * 50 + 200;
	}
	
	public void renderimage(){
		Graphics g = shipimageex.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, shipimageex.getWidth(), shipimageex.getHeight());
		g.drawImage(csm.shipimage, 0, 0, null);
		g.drawImage(drawimage, rectangle.x, rectangle.y, rectangle.width, rectangle.height, null);
		g.dispose();
	}
	
	public void update(){
		if (input.up.clicked) select --;
		if (input.down.clicked) select ++;
		
		if (input.left.clicked && select == 1) x --;
		if (input.right.clicked && select == 1) x ++;
		
		if (input.left.clicked && select == 2) y --;
		if (input.right.clicked && select == 2) y ++;
		
		if (input.left.clicked && select == 3) width --;
		if (input.right.clicked && select == 3) width ++;
		
		if (input.left.clicked && select == 4) height --;
		if (input.right.clicked && select == 4) height ++;
		
		if (width >= image.getWidth() * 2) width = image.getWidth() * 2;
		if (width <= image.getWidth() / 2) width = image.getWidth() / 2;
		
		if (height >= image.getHeight() * 2) height = image.getHeight() * 2;
		if (height <= image.getHeight() / 2) height = image.getHeight() / 2;
		
		rectangle = new Rectangle(x, y, width, height);
		
		if (input.enter.clicked && select == 5){
			csm.addShipComponent(image, rectangle);
			csm.setMenu(new CreateShipShapeMenu());
			csm.renderShip();
		}
	}
}
