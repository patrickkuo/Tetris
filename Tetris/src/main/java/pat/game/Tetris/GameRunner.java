package pat.game.Tetris;

import java.util.ArrayList;
import java.util.List;

public class GameRunner implements Runnable {

	private TetrisGame game;

	public GameRunner(TetrisGame game) {
		this.game = game;
	}

	public void run() {
		// TODO Auto-generated method stub
		
		synchronized (game) {
			
			while (!game.isGameEnd()) {
				if (game.getCurrentBlock()==null) {
					game.setCurrentBlock(randomBlock());
				}
				game.moveCurrentBlock(Movment.down);
				try {
					game.wait(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private Tetriminos randomBlock() {
		
		int random = (int) (Math.random()*Tetriminos.values().length);
		System.out.println(Tetriminos.values()[random]);
		return Tetriminos.values()[random];
	}


}
