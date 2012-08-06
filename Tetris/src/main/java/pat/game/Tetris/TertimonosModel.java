package pat.game.Tetris;

public class TertimonosModel {

	public static FieldCell[][] getModel(Tetriminos type) {

		FieldCell[][] result = new FieldCell[2][4];

		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[i].length; i++) {
				result[i][j] = new FieldCell();
			}
		}

		switch (type) {
		case I:
			for (int i = 0; i < 4; i++) {
				result[0][i].setFilled(true);
				result[0][i].setFilledTeriminos(Tetriminos.I);
			}
			break;
		case J:
			for (int i = 0; i < 3; i++) {
				result[0][i].setFilled(true);
				result[0][i].setFilledTeriminos(Tetriminos.J);
			}
			result[1][0].setFilled(true);
			result[1][0].setFilledTeriminos(Tetriminos.J);
			break;

		case L:
			for (int i = 0; i < 3; i++) {
				result[1][i].setFilled(true);
				result[1][i].setFilledTeriminos(Tetriminos.L);
			}
			result[0][0].setFilled(true);
			result[0][0].setFilledTeriminos(Tetriminos.L);
			break;

		case O:
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					result[i][j].setFilled(true);
					result[i][j].setFilledTeriminos(Tetriminos.O);
				}
			}
			break;

		case S:

			result[0][0].setFilled(true);
			result[0][0].setFilledTeriminos(Tetriminos.S);

			result[0][1].setFilled(true);
			result[0][1].setFilledTeriminos(Tetriminos.S);

			result[1][1].setFilled(true);
			result[1][1].setFilledTeriminos(Tetriminos.S);

			result[1][2].setFilled(true);
			result[1][2].setFilledTeriminos(Tetriminos.S);

			break;

		case T:
			for (int i = 0; i < 3; i++) {
				result[0][i].setFilled(true);
				result[0][i].setFilledTeriminos(Tetriminos.T);
			}
			result[1][1].setFilled(true);
			result[1][1].setFilledTeriminos(Tetriminos.T);
			break;

		case Z:
			result[1][0].setFilled(true);
			result[1][0].setFilledTeriminos(Tetriminos.S);

			result[1][1].setFilled(true);
			result[1][1].setFilledTeriminos(Tetriminos.S);

			result[0][1].setFilled(true);
			result[0][1].setFilledTeriminos(Tetriminos.S);

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
