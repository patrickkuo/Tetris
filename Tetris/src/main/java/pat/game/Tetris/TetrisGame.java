package pat.game.Tetris;

public class TetrisGame {

	private PlayField playField;
	private Tetriminos currentBlock;
	private boolean gameEnd;

	public TetrisGame() {
		playField = new PlayField();
		currentBlock = null;
		this.gameEnd = false;
	}


	public PlayField getPlayField() {
		return playField;
	}

	public void setCurrentBlock(Tetriminos current) {
		this.currentBlock = current;
		playField.setCurrentBlock(current);
	}
	
	

	public Tetriminos getCurrentBlock() {
		return currentBlock;
	}


	public void moveCurrentBlock(Movment move) {

		if (currentBlock != null) {
			switch (move) {

			case down:
				System.out.println("down");
				break;
			case left:
				System.out.println("left");
				break;
			case right:
				System.out.println("right");
				break;
			case rotate:
				System.out.println("rotate");
				break;

			default:
				System.out.println("error");
				break;
			}
		}
	}

	public boolean isGameEnd() {
		return gameEnd;
	}
}
