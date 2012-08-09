package pat.game.Tetris;

import javax.swing.JMenuItem;

public class GameThread extends Thread {

	private TetrisGame game;
	private boolean pause;
	private JMenuItem stopResume;

	public GameThread(TetrisGame game, JMenuItem stopResume) {
		this.game = game;
		this.stopResume = stopResume;
		resumeThread();
	}

	public void run() {
		// TODO Auto-generated method stub
		int blockDone = 0;
		synchronized (game) {

			while (!game.isGameEnd()) {

				while (pause) {
					synchronized (this) {

						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

				int gameSpeed = 500 - game.getScore() / 15;
				gameSpeed = (gameSpeed > 100) ? gameSpeed : 100;

				if (game.getNextBlock() == null) {
					game.setNextBlock(game.randomBlock());
				}
				if (game.getCurrentBlock() == null) {
					game.setCurrentBlock(game.randomBlock());
				} else if (game.getCurrentBlock().isDone()) {
					blockDone++;
					if (blockDone == (500 - game.getScore() / 50) / 25) {
						blockDone = 0;
						game.pushUP();
					}
					game.setCurrentBlock(game.getNextBlock());
					game.setNextBlock(game.randomBlock());
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

	public void pauseThread() {
		stopResume.setText("Resume");
		game.setPause(true);
		this.pause = true;
	}

	public void resumeThread() {
		stopResume.setText("Pause");
		game.setPause(false);
		this.pause = false;

		synchronized (this) {
			notify();
		}

	}

	public void toggle() {
		if (this.pause) {
			resumeThread();
		} else {
			pauseThread();
		}
	}

}
