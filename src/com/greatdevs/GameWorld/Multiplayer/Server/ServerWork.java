package com.greatdevs.GameWorld.Multiplayer.Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import com.greatdevs.GameWorld.MultiPlayer;
import com.greatdevs.GameWorld.Multiplayer.Menu.ConnectMenu;
import com.greatdevs.GameWorld.Multiplayer.Menu.CreateServer;
import com.greatdevs.GameWorld.Multiplayer.Menu.SelectShipMenu;

public class ServerWork {
	
	public static ObjectOutputStream out;
	public static ObjectInputStream in;
	public static ServerSocket server;	//Server side's server
	public static Socket client;		//Server side client's socket
	public static Socket socket;		//Client side socket
	public static String serverIP = "localhost"; 	//Server socket will bind to this address
	public static String connectIP = "localhost"; //Client will connect to this address
	public static int port = 25566;	

	public int stary, starsize, spawn;
	
	public void runServer(MultiPlayer w, String ip, int port){
		try{
		ServerWork.serverIP = ip;
		ServerWork.port = port;
		System.out.println("Hosting...");
		server = new ServerSocket(port, 4, InetAddress.getByName(serverIP));
		System.out.println("Ready!\nAwaiting client...");
		client = server.accept();
		System.out.println("Client connected!\nBuffering...");
		w.game.setMenu(new SelectShipMenu(w));
		//w.game.setMenu(null);
		out = new ObjectOutputStream(client.getOutputStream());
		in = new ObjectInputStream(client.getInputStream());
		System.out.println("Buffered!\nPinging for 256 bytes...");
		long start = System.currentTimeMillis();
		byte[] ping = new byte[256];
		in.read(ping);
		System.out.println("Latency: "+(System.currentTimeMillis()-start));
		out.writeLong(start);
		out.flush();
		System.out.println("Starting threads...");
		new ThreadSend(w, out);
		new ThreadReceive(w, in);
		System.out.println("Started!\nCreating game world...");
		} catch(Exception e){
			e.printStackTrace();
			w.game.setMenu(new CreateServer(w));
			CreateServer.ERROR();
		}
	}
	public void Connect(MultiPlayer w, String ip, int port){
		try{
		ServerWork.connectIP = ip;
		ServerWork.port = port;
		System.out.println("Connecting...");
		socket = new Socket(connectIP, port);
		System.out.println("Connected!\nBuffering...");
		w.game.setMenu(new SelectShipMenu(w));
		//w.game.setMenu(null);
		in = new ObjectInputStream(socket.getInputStream());
		out = new ObjectOutputStream(socket.getOutputStream());
		byte[] ping = new byte[256];
		new Random().nextBytes(ping);
		System.out.println("Buffered\nPinging for 256 bytes...");
		out.write(ping);
		out.flush();
		long latency = in.readLong();
		System.out.println("Latency: "+(System.currentTimeMillis()-latency));
		System.out.println("Starting threads...");
		new ThreadReceive(w, in);
		new ThreadSend(w, out);
		System.out.println("Started!\nCreating game world...");
		} catch(Exception e){
			e.printStackTrace();
			w.game.setMenu(new ConnectMenu(w));
			ConnectMenu.error = true;
		}
	}
}
