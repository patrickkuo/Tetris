package pat.game.Tetris;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TetrisGame {

	private static final int GAME_HEIGHT = 22;
	private static final int GAME_WIDTH = 10;
	private PlayField playField;
	private Block currentBlock;
	private boolean gameEnd;
	private TetrisCanvas canvas;
	private Block nextBlock;
	private Block storedBlock;
	private int score;
	private boolean pause;
	private List<Block> randomList;

	public int getScore() {
		return score;
	}

	public Block getNextBlock() {
		return nextBlock;
	}

	public void setNextBlock(Block nextBlock) {
		this.nextBlock = nextBlock;
	}

	public Block getStoredBlock() {
		return storedBlock;
	}

	public void setStoredBlock(Block storedBlock) {
		this.storedBlock = storedBlock;
	}

	public TetrisGame() {
		playField = new PlayField();
		currentBlock = null;
		this.gameEnd = false;
		this.randomList = new LinkedList<Block>();
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

		if (currentBlock != null && !pause) {
			switch (move) {

			case down:
				currentBlock.moveDown();
				break;
			case left:
				currentBlock.moveLeft();
				break;
			case right:
				currentBlock.moveRight();
				break;
			case rotate:
				currentBlock.rotate();
				break;
			default:
				System.out.println("error");
				break;
			}
			synchronized (canvas) {
				canvas.notifyAll();
			}

			if (currentBlock.isDone()) {
				removeFullRow();
				checkLose();
			}

		}
	}

	public boolean isGameEnd() {
		return gameEnd;
	}

	public TetrisCanvas getCanvas() {
		return canvas;
	}

	public void setCanvas(TetrisCanvas canvas) {
		this.canvas = canvas;
	}

	public void removeFullRow() {

		List<List<FieldCell>> gameFieldCells = this.playField.getPlayField();
		int removedCount = 0;
		for (int i = GAME_HEIGHT - 1; i > -1; i--) {
			int cellCounter = 0;
			for (List<FieldCell> column : gameFieldCells) {
				if (column.get(i).isFilled()) {
					cellCounter++;
				}
			}
			if (cellCounter == GAME_WIDTH) {

				for (List<FieldCell> column : gameFieldCells) {

					LinkedList<FieldCell> tmpList = (LinkedList<FieldCell>) column;
					tmpList.remove(i);
					tmpList.push(new FieldCell());
				}

				removedCount++;
				i++;
			}
		}

		if (removedCount == 4) {
			removedCount = removedCount * 2;
		}
		score = score + removedCount * 100;
	}

	public void saveBlock() {

		if (this.storedBlock == null) {
			this.storedBlock = randomBlock();
		}

		Block tmpBlock = nextBlock;
		nextBlock = storedBlock;
		storedBlock = tmpBlock;

	}

	public Block randomBlock() {

		if (randomList.isEmpty()) {

			for (Tetriminos type : Tetriminos.values()) {
				for (int i = 0; i < 10; i++) {
					randomList.add(new Block(type));
				}
			}
		}

		int random = (int) (Math.random() * randomList.size());

		Block result = randomList.get(random);
		randomList.remove(random);

		return result;
	}

	private void checkLose() {
		for (List<FieldCell> column : playField.getPlayField()) {
			if (column.get(1).isFilled()) {
				this.gameEnd = true;
				// System.out.println(playField);
			}
		}
	}

	public void pushUP() {

		for (List<FieldCell> column : playField.getPlayField()) {

			int random = (int) (Math.random() * 10);
			FieldCell newBlock = null;
			if (random > 3) {
				newBlock = new FieldCell(true, null);
				column.set(0, newBlock);
			}
			Collections.rotate(column, -1);
		}
		checkLose();
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}
}
