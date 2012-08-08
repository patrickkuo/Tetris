package pat.game.Tetris;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlayField implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int GAME_HEIGHT = 22;
	private static final int GAME_WIDTH = 10;

	private List<List<FieldCell>> playField;
	private Block currentBlock;

	public PlayField() {
		clearField();
	}

	public List<List<FieldCell>> getPlayField() {
		return this.playField;
	}

	public void clearField() {

		this.playField = new ArrayList<List<FieldCell>>();

		for (int i = 0; i < GAME_WIDTH; i++) {
			this.playField.add(new LinkedList<FieldCell>());
		}

		for (List<FieldCell> column:this.playField) {
			for (int j = 0; j < GAME_HEIGHT; j++) {
				column.add(new FieldCell());
			}
		}

	}

	public Block getCurrentBlock() {
		return currentBlock;
	}

	public String toString() {

		StringBuffer result = new StringBuffer();

		for (List<FieldCell> column : this.playField) {
			for (FieldCell cell : column) {
				if (cell.isFilled()) {
					result.append("■");
				} else {
					result.append("□");
				}
			}
			result.append("\n");
		}
		return result.toString();
	}

	public void setCurrentBlock(Block current) {
		// TODO Auto-generated method stub
		this.currentBlock = current;
	}

}
