package pat.game.Tetris;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		TetrisGame game = new TetrisGame();
		new TetrisGUI(game.getPlayField());


			Thread thread = new Thread(new GameRunner(game));
			thread.start();
		

	}
}
