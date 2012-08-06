package pat.game.Tetris;

public class PlayField {

	private FieldCell playField[][];
	private Tetriminos currentBlock;
	

	public PlayField() {
		clearField();
	}

	public FieldCell[][] getPlayField() {
		return this.playField;
	}

	public void clearField() {

		this.playField = new FieldCell[22][10];

		for (int i = 0; i < playField.length; i++) {
			for (int j = 0; j < playField[i].length; j++) {
				playField[i][j] = new FieldCell();
			}
		}

	}

	public String toString() {

		StringBuffer result = new StringBuffer();

		for (FieldCell[] column : playField) {
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

	public void setCurrentBlock(Tetriminos current) {
		// TODO Auto-generated method stub
		this.currentBlock = current;
		
		
	}

}
