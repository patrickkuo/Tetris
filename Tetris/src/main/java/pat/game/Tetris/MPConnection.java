package pat.game.Tetris;

import java.io.*;
import java.net.*;

public class MPConnection {

	public static void send(TetrisGUI mainFrame, String ip) {

		mainFrame.newGame();


		try {
			InetAddress addr = InetAddress.getLocalHost();
			String hostIP = addr.getHostAddress();
			
			Socket s = new Socket(ip, 1234);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			MPGameData data = new MPGameData(mainFrame.getGame().getPlayField(), hostIP);
			out.writeObject(data);
			out.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void recieve() {
		
		try {
			
			ServerSocket server = new ServerSocket(1234);
			Socket s = server.accept();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			MPGameData objectReceived = (MPGameData) in.readObject();
			
			if(objectReceived==null){
				System.out.println("waiting");
			}else{
				System.out.println("connected!!");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
