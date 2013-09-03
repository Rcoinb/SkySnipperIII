package com.greatdevs;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import com.greatdevs.Menu.*;
import com.greatdevs.Save.Save;
import com.greatdevs.Save.SaveOptions;
import com.greatdevs.Sound.Sound;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.greatdevs.Entity.Player;
import com.greatdevs.GameUpdate.Update;
import com.greatdevs.Image.Icons;
import com.greatdevs.Image.ShipIcons;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
	
	public static final String TITLE = "Sky Snipper III";
	public static final int WIDTH = 20;
	public static final int HEIGHT = 10;
	public static final int SCALE = 50;
	
	public static final int minWidth = 500, minHeight = 250;
	
	public static boolean MENU = false;
	
	private boolean running = false;
	
	public InputHandler input = new InputHandler(this);
	public Icons icons = new Icons();
	public ShipIcons shipicons = new ShipIcons();
	public Menu menu;
	public Update update = new Update();
	public Save save = new Save();
	public SaveOptions saveoptions = new SaveOptions();
	
	public static JFrame frame;
	
	public Game(){
		frame = new JFrame(Game.TITLE);
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
		if (menu != null) menu.init(this, input);
		if (menu != null) MENU = true;
		if (menu == null) MENU = false;
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	private void init() {
		try {
			save.loadgame();
			saveoptions.loadOptions();
		} catch (Exception e) {
			e.printStackTrace();
		}
		save.loadallgame(this);
		setMenu(new GDMenu());
		Sound.PlayMusic("music.wav", StaticGameOptions.MUSIC_VOLUME);
	}

	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int updates = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (running) {
		    long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				updates++;
				update();
				resolutionUpdate();
				MusicUpdate();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
				paintScreen();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println("updates: " + updates + ", fps: " + frames);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void MusicUpdate(){
		if (!StaticGameOptions.PLAY_MUSIC){
			Sound.StopMusic();
		}
	}
	
	public void resolutionUpdate(){
		if (frame.getWidth() < minWidth){
			frame.setSize(minWidth, minHeight);
		}
		if (frame.getHeight() < minHeight){
			frame.setSize(minWidth, minHeight);
		}
	}
	
	public void update() {
		if (hasFocus()){
	        input.update();
	        save.saveallgame(this);
	        if (MENU){
				menu.update();
		        if (input.up.clicked || input.down.clicked || input.left.clicked || input.right.clicked) Sound.play("select.wav");
		        if (input.enter.clicked || input.menu.clicked) Sound.play("button.wav");
			}
	        else if (!MENU){
		        update.update(this);
		        if (input.menu.clicked) setMenu(new PauseMenu());
			}
		}
		else if (!hasFocus()){
			input.releaseAll();
		}
	}

	public void render() {	
		Graphics g = image.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
        
        //Paint            
		update.render.render(g, update);
		g.setFont(new Font("Arial", Font.BOLD, 25));
		int strw1 = (int) g.getFontMetrics().getStringBounds("Score " + update.gameworld.SCORE, g).getWidth();
		g.setColor(Color.WHITE);
		g.drawString("Score " + update.gameworld.SCORE, (((Game.WIDTH  * Game.SCALE) - strw1) - 25), 25);
		int strw2 = (int) g.getFontMetrics().getStringBounds("Coins " + update.gameworld.COINS, g).getWidth();
		g.setColor(Color.WHITE);
		g.drawString("Coins " + update.gameworld.COINS, (((Game.WIDTH  * Game.SCALE) - strw2) - 25), 490);
		
		for (final Player player : update.entity.playerarray){
			if (player.superpower && !player.superpowerinuse){
					g.setColor(Color.YELLOW);
					g.setFont(new Font("Arial", Font.BOLD, 25));
					int title5w = (int) g.getFontMetrics().getStringBounds("Press x to use super power", g).getWidth();
					g.drawString("Press x to use super power", (((Game.WIDTH  * Game.SCALE) / 2) - (title5w / 2)), 30);
			}
		}
		
		if (MENU){
			menu.render(g);
		}
		
		if (!hasFocus()){
			noFocusRender(g);
		}
        //Paint
		g.dispose();
	}
	
	public void noFocusRender(Graphics g){
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, (WIDTH * SCALE), (HEIGHT * SCALE));
		g.drawImage(icons.focuslable, (((WIDTH * SCALE) / 2) - (icons.focuslable.getWidth() / 2)), (((HEIGHT * SCALE) / 2) - (icons.focuslable.getHeight() / 2)), null);
	}
	
	public void paintScreen(){
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			requestFocus();
			return;
		}		
		Graphics g = bs.getDrawGraphics();		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	
	private static void SetupGame(){
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(Icons.frameicon);
		frame.setVisible(true);
		
		game.save.createdirectory();
		game.start();
	}
	
	public static void main(String[] args) {
		SetupGame();
	}
}