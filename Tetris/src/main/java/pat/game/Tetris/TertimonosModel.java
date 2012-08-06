package pat.game.Tetris;

public class TertimonosModel {

	public static FieldCell[][] getModel(Tetriminos type) {

		FieldCell[][] result = null;

		switch (type) {
		case I:
			result = new FieldCell[1][4];
			for (int i = 0; i < 4; i++) {
				result[0][i] = new FieldCell();
				result[0][i].setFilled(true);
				result[0][i].setFilledTeriminos(Tetriminos.I);
			}
			break;
		case L:
			result = new FieldCell[2][3];
			for (int i = 0; i < 3; i++) {
				result[0][i] = new FieldCell();
				result[0][i].setFilled(true);
				result[0][i].setFilledTeriminos(Tetriminos.L);
			}
			result[1][0] = new FieldCell();
			result[1][0].setFilled(true);
			result[1][0].setFilledTeriminos(Tetriminos.L);
			break;

		case J:
			result = new FieldCell[2][3];
			for (int i = 0; i < 3; i++) {
				result[1][i] = new FieldCell();
				result[1][i].setFilled(true);
				result[1][i].setFilledTeriminos(Tetriminos.J);
			}
			result[0][0] = new FieldCell();
			result[0][0].setFilled(true);
			result[0][0].setFilledTeriminos(Tetriminos.J);
			break;

		case O:
			result = new FieldCell[2][2];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					result[i][j] = new FieldCell();
					result[i][j].setFilled(true);
					result[i][j].setFilledTeriminos(Tetriminos.O);
				}
			}
			break;

		case Z:
			result = new FieldCell[2][3];
			result[0][0] = new FieldCell();
			result[0][0].setFilled(true);
			result[0][0].setFilledTeriminos(Tetriminos.Z);
			result[0][1] = new FieldCell();
			result[0][1].setFilled(true);
			result[0][1].setFilledTeriminos(Tetriminos.Z);
			result[1][1] = new FieldCell();
			result[1][1].setFilled(true);
			result[1][1].setFilledTeriminos(Tetriminos.Z);
			result[1][2] = new FieldCell();
			result[1][2].setFilled(true);
			result[1][2].setFilledTeriminos(Tetriminos.Z);

			break;

		case T:
			result = new FieldCell[2][3];
			for (int i = 0; i < 3; i++) {
				result[0][i] = new FieldCell();
				result[0][i].setFilled(true);
				result[0][i].setFilledTeriminos(Tetriminos.T);
			}
			result[1][1] = new FieldCell();
			result[1][1].setFilled(true);
			result[1][1].setFilledTeriminos(Tetriminos.T);
			break;

		case S:
			result = new FieldCell[2][3];
			result[1][0] = new FieldCell();
			result[1][0].setFilled(true);
			result[1][0].setFilledTeriminos(Tetriminos.S);
			result[1][1] = new FieldCell();
			result[1][1].setFilled(true);
			result[1][1].setFilledTeriminos(Tetriminos.S);
			result[0][1] = new FieldCell();
			result[0][1].setFilled(true);
			result[0][1].setFilledTeriminos(Tetriminos.S);
			result[0][2] = new FieldCell();
			result[0][2].setFilled(true);
			result[0][2].setFilledTeriminos(Tetriminos.S);

			break;

		default:
			System.out.println("error");
			break;
		}

		return result;

	}

}
