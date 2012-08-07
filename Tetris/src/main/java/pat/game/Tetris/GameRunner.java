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
					game.setNextBlock(randomBlock());
				}
				if (game.getCurrentBlock()==null) {
					game.setCurrentBlock(randomBlock());
				}else if(game.getCurrentBlock().isDone()){
					game.setCurrentBlock(game.getNextBlock());
					game.setNextBlock(randomBlock());
				}
				game.moveCurrentBlock(Movment.down);
				try {
					game.wait(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private Block randomBlock() {
		
		int random = (int) (Math.random()*Tetriminos.values().length);
		System.out.println(Tetriminos.values()[random]);
		Tetriminos randomBlock = Tetriminos.values()[random];
		
		return new Block(randomBlock);
	}


}
