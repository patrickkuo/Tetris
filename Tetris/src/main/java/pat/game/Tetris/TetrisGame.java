package pat.game.Tetris;

public class TetrisGame {

	private PlayField playField;
	private Block currentBlock;
	private boolean gameEnd;

	public TetrisGame() {
		playField = new PlayField();
		currentBlock = null;
		this.gameEnd = false;
	}


	public PlayField getPlayField() {
		return playField;
	}

	public void setCurrentBlock(Block current) {
		this.currentBlock = current;
		playField.setCurrentBlock(current);
		currentBlock.setGameField(playField);
	}
	
	public Block getCurrentBlock() {
		return currentBlock;
	}


	public void moveCurrentBlock(Movment move) {

		if (currentBlock != null) {
			switch (move) {

			case down:
				currentBlock.moveDown();
				break;
			case left:
				System.out.println("left");
				break;
			case right:
				System.out.println("right");
				break;
			case rotate:
				currentBlock.rotate();
				break;
			default:
				System.out.println("error");
				break;
			}
			gameEnd = currentBlock.isGameEnd();
		}
	}

	public boolean isGameEnd() {
		return gameEnd;
	}
}
