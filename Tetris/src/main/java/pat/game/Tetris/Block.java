package pat.game.Tetris;

public class Block {

	private FieldCell[][] model;
	private int x, y;
	private PlayField gameField;
	private Tetriminos type;
	private boolean done;
	private boolean gameEnd;

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
			if (!downCollision()) {
				cleanOld();
				y++;
				addNew();
			} else {
				checkLose();
			}
			// System.out.println(gameField);
		}
	}

	public void rotate() {
		if (gameField != null) {
			cleanOld();
			int oldX = model.length;
			int oldY = model[0].length;
			FieldCell[][] newModel = new FieldCell[oldY][oldX];

			for (int i = 0; i < oldX; i++) {
				for (int j = 0; j < oldY; j++) {
					if (model[i][j] != null) {
						newModel[oldY-j-1][i] = model[i][j];
					}
				}
			}
			this.model = newModel;

			addNew();

		}
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

	private boolean downCollision() {

		if (model.length + 1 + this.y > 22) {
			this.done = true;
			return true;
		} else {
			for (int i = 0; i < model[model.length - 1].length; i++) {
				if (model[model.length - 1][i] != null
						&& model[model.length - 1][i].isFilled()) {
					if (gameField.getPlayField()[y + model.length][x + 3 + i]
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

	private void checkLose() {
		for (int i = 0; i < gameField.getPlayField()[1].length; i++) {
			if (gameField.getPlayField()[1][i].isFilled()) {
				this.gameEnd = true;
			}
		}
	}

	public boolean isGameEnd() {
		return gameEnd;
	}
}
