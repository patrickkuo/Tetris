package pat.game.Tetris;

import java.io.*;
import java.net.*;

public class MPConnection {

	public static void send(TetrisGUI mainFrame, String ip,int port) {

		mainFrame.newGame();


		try {
			InetAddress addr = InetAddress.getLocalHost();
			String hostIP = addr.getHostAddress();
			
			Socket s = new Socket(ip, port);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			MPGameData data = new MPGameData(mainFrame.getGame().getPlayField(), hostIP);
			out.writeObject(data);
			out.flush();
			
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void recieve(int port) {
		
		try {
			
			ServerSocket server = new ServerSocket(port);
			Socket s = server.accept();
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			MPGameData objectReceived = (MPGameData) in.readObject();
			
			if(objectReceived==null){
				System.out.println("waiting");
			}else{
				System.out.println("connected!!");
			}
			
			s.close();
			server.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
