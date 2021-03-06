package com.greatdevs;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import com.greatdevs.Luancher.Launcher;
import com.greatdevs.Menu.*;
import com.greatdevs.Save.Save;
import com.greatdevs.Save.SaveOptions;
import com.greatdevs.Sound.Sound;

import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.greatdevs.GameUpdate.Update;
import com.greatdevs.GameWorld.GameMode;
import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.Image.Icons;
import com.greatdevs.Image.ShipIcons;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = new BufferedImage(WIDTH * SCALE, HEIGHT * SCALE, BufferedImage.TYPE_INT_RGB);
	
	public static final int DefaultlauncherID = 0x27E641;
	
	public static final String TITLE = "Sky Snipper III";
	public static final int WIDTH = 20;
	public static final int HEIGHT = 10;
	public static final int SCALE = 50;
	
	public static boolean LaunchedFromLauncher = false;
	
	public static final int minWidth = 500, minHeight = 250;
	
	public static boolean MENU = false;
	
	public String FPSinfo = "updates: 0, fps: 0";
	
	private boolean running = false;
	
	public InputHandler input = new InputHandler(this);
	public Icons icons = new Icons();
	public ShipIcons shipicons = new ShipIcons();
	public Menu menu;
	public GameMode gamemode;
	public Update update = new Update();
	public Save save = new Save();
	public SaveOptions saveoptions = new SaveOptions();
	
	public static int GAME_MODE;
	
	public static JFrame frame;
	
	public Game(){
		frame = new JFrame(Game.TITLE);
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
		if (menu != null) menu.init(this, input);
		if (menu != null){
			MENU = true;
		}
		if (menu == null) MENU = false;
		System.out.println("Menu was set: " + menu);
	}
	
	public void setGameMode(GameMode gamemode) {
		this.gamemode = gamemode;
		setMenu(null);
		if (gamemode != null) gamemode.init(this, input);
		GAME_MODE = GameMode.GAMEMODE;
		if (gamemode == null) GAME_MODE = 0;
		System.out.println("GameMode was set: " + gamemode);
	}
	
	public void start() {
		running = true;
		new Thread(this).start();
	}

	public void stop() {
		running = false;
	}

	private void init() {
		save.loadgame();
		try {
			saveoptions.loadOptions();
			System.out.println("Saves: loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
		save.loadallgame(this);
		setMenu(new GDMenu());
		Sound.PlayMusic("music.wav", StaticGameOptions.MUSIC_VOLUME);
		System.out.println("Ready");
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
				FPSinfo = ("updates: " + updates + ", fps: " + frames);
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
		if (hasFocus() || (GAME_MODE == MultiPlayer.MULTIPLAYER)){
	        input.update();
	        save.saveallgame(this);
	        if (MENU){
				menu.update();
		        if (input.up.clicked || input.down.clicked || input.left.clicked || input.right.clicked) Sound.play("select.wav");
		        if (input.enter.clicked || input.menu.clicked) Sound.play("button.wav");
			}
	        else if (!MENU){
		        if (GAME_MODE != 0){
		        	gamemode.update();
		        }
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
        if (GAME_MODE != 0){
        	gamemode.render(g);
        	gamemode.renderGUI(g);
        }
		
		if (MENU){
			menu.render(g);
		}
		
		/*g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.WHITE);
		g.drawString(FPSinfo, 2, 10);*/
		
		if (!hasFocus()){
			noFocusRender(g);
		}
		
		if (!LaunchedFromLauncher) g.drawImage(Icons.devsign, 0, 0, null);
		
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
		
		System.out.println("Display: loaded");
		
		game.save.createdirectory();
		game.start();
	}
	
	public static void PathUpdate(){
		if (Verify.isWindows()){
			StaticGameOptions.PATH = (System.getProperty("user.home") + "\\AppData\\Roaming\\");
			System.out.println("OS: Windows\nFile path: " + StaticGameOptions.PATH);
		}
		else if (Verify.isMac()){
			StaticGameOptions.PATH = (System.getProperty("user.home") + "/Documents/");
			System.out.println("OS: Mac\nFile path: " + StaticGameOptions.PATH);
		}
		else {
			StaticGameOptions.PATH = (System.getProperty("user.home") + "");
			System.out.println("OS: Unrecognized\nFile path: " + StaticGameOptions.PATH);
		}
	}
	
	public static void main(String[] args) {
		new Launcher().setupLauncher();
	}
	
	public static void startGame() {
		System.out.println("Starting game...");
		PathUpdate();
		SetupGame();
	}
}