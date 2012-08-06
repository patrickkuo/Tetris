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
		if (gameField != null && !done) {
			if (!leftCollision()) {
				cleanOld();
				x--;
				addNew();
			}
		}
	}

	public void moveRight() {
		if (gameField != null&& !done) {
			if (!rightCollision()) {
				cleanOld();
				x++;
				addNew();
			}
		}

	}

	public void moveDown() {
		if (gameField != null&& !done) {
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
		if (gameField != null&& !done) {
			if (!rotateCollision()) {
				cleanOld();
				int oldX = model.length;
				int oldY = model[0].length;
				FieldCell[][] newModel = new FieldCell[oldY][oldX];

				for (int i = 0; i < oldX; i++) {
					for (int j = 0; j < oldY; j++) {
						if (model[i][j] != null) {
							newModel[oldY - j - 1][i] = model[i][j];
						}
					}
				}

				int delta = newModel.length - this.model.length;
				y = (y - delta > -1) ? y - delta : 0;
				this.model = newModel;

				addNew();

			}
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

	private boolean leftCollision() {

		if (x + 2 < 0) {
			return true;
		}

		for (int i = 0; i < model.length; i++) {
			if (model[i][0] != null
					&& this.gameField.getPlayField()[i + y][x + 2].isFilled()) {
				return true;
			}
		}

		return false;
	}

	private boolean rightCollision() {

		if (x + 3 + model[0].length > 9) {
			return true;
		}
		for (int i = 0; i < model.length; i++) {
			if (model[i][model[0].length - 1] != null
					&& this.gameField.getPlayField()[i + y][x + 3
							+ model[0].length].isFilled()) {
				return true;
			}
		}

		return false;
	}

	private boolean downCollision() {

		if (model.length + 1 + this.y > 22) {
			this.done = true;
			return true;
		}
		cleanOld();
		for (int i = 0; i < model.length; i++) {
			for (int j = 0; j < model[i].length; j++) {

				if (model[i][j] != null) {
					if (gameField.getPlayField()[y + i + 1][x + 3 + j]
							.isFilled()) {
						addNew();
						this.done = true;
						return true;
					}
				}
			}
		}
		addNew();
		return false;

	}

	private boolean rotateCollision() {

		try{
		cleanOld();

		int oldX = model.length;
		int oldY = model[0].length;
		FieldCell[][] newModel = new FieldCell[oldY][oldX];

		for (int i = 0; i < oldX; i++) {
			for (int j = 0; j < oldY; j++) {
				if (model[i][j] != null) {
					newModel[oldY - j - 1][i] = model[i][j];
				}
			}
		}

		int delta = newModel.length - this.model.length;
		int tmpY = (y - delta > -1) ? y - delta : 0;

		boolean result = false;
		for (int i = 0; i < newModel.length; i++) {
			for (int j = 0; j < newModel[i].length; j++) {
				if (newModel[i][j] != null
						&& this.gameField.getPlayField()[i + tmpY][j + x + 3]
								.isFilled()) {
					result = true;
				}

			}
		}

		addNew();
		return result;
		}catch (ArrayIndexOutOfBoundsException e){
			addNew();
			return true;
		}
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
