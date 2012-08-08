package pat.game.Tetris;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Reciever implements Runnable {

	private TetrisGUI gameGUI;

	public Reciever(TetrisGUI gameGUI) {

		this.gameGUI = gameGUI;

	}

	public void run() {

		System.out.println("running");

		while (!gameGUI.getGame().isGameEnd()) {
			System.out.println("inside");
			
			MPConnection.recieve();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
