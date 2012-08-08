package pat.game.Tetris;

public class Sender implements Runnable {

	private TetrisGUI mainFrame;
	private String ip;
	private int port;

	public Sender(TetrisGUI mainFrame, String ip, int port) {
		this.mainFrame = mainFrame;
		this.ip = ip;
		this.port = port;

	}

	public void run() {
		System.out.println("running");

		while (!mainFrame.getGame().isGameEnd()) {
			
			MPConnection.send(mainFrame, ip, port);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
