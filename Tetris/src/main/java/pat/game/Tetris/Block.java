package pat.game.Tetris;

public class Block {

	private FieldCell[][] model;
	private int x, y;
	private PlayField gameField;
	private Tetriminos type;
	private boolean done;

	public Block(Tetriminos type) {
		this.model = TertimonosModel.getModel(type);
		this.x = 0;
		this.y = 0;
		this.type = type;
	}

	public void setGameField(PlayField gameField) {
		this.gameField = gameField;
		addNew();
	}

	public void moveLeft() {

	}

	public void moveRight() {

	}

	public void moveDown() {
		if (gameField != null) {
			if (!checkCollision(0, 1)) {
				cleanOld();
				y++;
				addNew();
			}
			//System.out.println(gameField);
		}
	}

	public void rotate() {

	}

	private void cleanOld() {

		for (int i = 0; i < model.length; i++) {
			for (int j = 0; j < model[i].length; j++) {

				if (model[i][j] != null && model[i][j].isFilled()) {
					this.gameField.getPlayField()[i + y][j + x + 3].empty();
				}
			}
		}
	}

	private void addNew() {
		for (int i = 0; i < model.length; i++) {
			for (int j = 0; j < model[i].length; j++) {

				if (model[i][j] != null && model[i][j].isFilled()) {
					this.gameField.getPlayField()[i + y][j + x + 3].fill(type);
				}
			}
		}
	}

	private boolean checkCollision(int xD, int yD) {

		if (model.length + yD + this.y > 22) {
			System.out.println("called");
			this.done = true;
			return true;
		} else {
			for (int i = 0; i < model[model.length - 1].length; i++) {
				if (model[model.length - 1][i] != null
						&& model[model.length - 1][i].isFilled()) {
					if (gameField.getPlayField()[y + yD +model.length - 1][xD + x + 3 + i]
							.isFilled()) {
						this.done = true;
						return true;
					}
				}
			}
		}

		return false;

	}

	public boolean isDone() {
		return this.done;
	}
}
