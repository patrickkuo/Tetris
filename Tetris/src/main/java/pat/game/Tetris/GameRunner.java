package pat.game.Tetris;

public class GameRunner implements Runnable {

	private TetrisGame game;

	public GameRunner(TetrisGame game) {
		this.game = game;
	}

	public void run() {
		// TODO Auto-generated method stub
		
		synchronized (game) {
			
			while (!game.isGameEnd()) {
				
				if (game.getNextBlock()==null) {
					game.setNextBlock(TetrisGame.randomBlock());
				}
				if (game.getCurrentBlock()==null) {
					game.setCurrentBlock(TetrisGame.randomBlock());
				}else if(game.getCurrentBlock().isDone()){
					game.setCurrentBlock(game.getNextBlock());
					game.setNextBlock(TetrisGame.randomBlock());
				}
				game.moveCurrentBlock(Movment.down);
				
				int gameSpeed = 500 - game.getScore()/10;
				
				try {
					game.wait((gameSpeed>50)?gameSpeed:50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	



}
