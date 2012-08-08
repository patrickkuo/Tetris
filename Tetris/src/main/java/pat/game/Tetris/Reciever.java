package pat.game.Tetris;

public class Reciever implements Runnable {

	private TetrisGUI gameGUI;
	private int port;

	public Reciever(TetrisGUI gameGUI,int port) {

		this.gameGUI = gameGUI;
		this.port = port;

	}

	public void run() {

		System.out.println("running");
		gameGUI.newMPGame();
		gameGUI.start();
		
		while (!gameGUI.getGame().isGameEnd()) {
			System.out.println("inside");
			
			MPConnection.recieve(port,gameGUI);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
