package pat.game.Tetris;

public class GameRunner implements Runnable {

	private TetrisGame game;

	public GameRunner(TetrisGame game) {
		this.game = game;
	}

	public void run() {
		// TODO Auto-generated method stub
		int blockDone = 0;
		synchronized (game) {

			while (!game.isGameEnd()) {
				int gameSpeed = 500 - game.getScore() / 15;
				gameSpeed = (gameSpeed > 100) ? gameSpeed : 100;

				if (game.getNextBlock() == null) {
					game.setNextBlock(TetrisGame.randomBlock());
				}
				if (game.getCurrentBlock() == null) {
					game.setCurrentBlock(TetrisGame.randomBlock());
				} else if (game.getCurrentBlock().isDone()) {
					blockDone++;
					if (blockDone == gameSpeed/40) {
						blockDone = 0;
						game.pushUP();
					}
					game.setCurrentBlock(game.getNextBlock());
					game.setNextBlock(TetrisGame.randomBlock());
				}
				game.moveCurrentBlock(Movment.down);

				try {
					game.wait(gameSpeed);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					return;
				}
			}
		}
	}

}
