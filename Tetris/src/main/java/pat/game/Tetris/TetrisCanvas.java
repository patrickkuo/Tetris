package pat.game.Tetris;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JPanel;

public class TetrisCanvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayField playField;
	private static final int BLOCK_WIDTH = 30;
	private BufferedImage bi;
	private Graphics2D g2d;
	private TetrisGame game;
	private TetrisGame game2;

	public TetrisCanvas(TetrisGame game) {

		this.game = game;
		this.playField = game.getPlayField();

	}

	public TetrisCanvas(TetrisGame game1, TetrisGame game2) {

		this.game = game1;
		this.game2 = game2;
		this.playField = game.getPlayField();

	}

	@Override
	public void paintComponent(Graphics graphics) {

		// Graphics2D g2 = (Graphics2D) graphics;

		if (this.bi == null) {
			this.bi = (BufferedImage) createImage(850, 700);
		}

		if (this.g2d == null) {
			this.g2d = bi.createGraphics();

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		int startPos = (game2==null)?6*BLOCK_WIDTH:0;

		g2d.clearRect(0, 0, 850, 700);
		drawNextBlock(g2d, startPos, 0);
		drawSaveBlock(g2d, startPos, 0);
		drawScore(g2d, startPos, 0);
		drawBackGround(g2d, startPos, 0);
		drawGrid(g2d, game.getPlayField().getPlayField(), startPos, 0);

		if (game2 != null) {
			drawBackGround(g2d, 16 * BLOCK_WIDTH, 0);
			drawGrid(g2d, game2.getPlayField().getPlayField(), 16 * BLOCK_WIDTH, 0);
		}

		graphics.drawImage(bi, 0, 0, this);

	}

	private void drawGrid(Graphics2D g2d, List<List<FieldCell>> fieldCell,
			int x, int y) {

		for (List<FieldCell> column : fieldCell) {
			for (int i = 2; i < 22; i++) { // display only 20 rows
				FieldCell cell = column.get(i);
				if (cell.isFilled()) {

					g2d.setColor(getColor(cell.getFilledTeriminos()));

					g2d.fillRoundRect(fieldCell.indexOf(column) * BLOCK_WIDTH
							+ 30 + x, column.indexOf(cell) * BLOCK_WIDTH - 25
							+ y, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(fieldCell.indexOf(column) * BLOCK_WIDTH
							+ 30 + x, column.indexOf(cell) * BLOCK_WIDTH - 25
							+ y, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
				}
			}
		}
	}

	private void drawBackGround(Graphics2D g2d, int x, int y) {

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);

		g2d.fillRoundRect(28 + x, 2 * BLOCK_WIDTH - 27 + y,
				BLOCK_WIDTH * 10 + 4, BLOCK_WIDTH * 20 + 4, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(28 + x, 2 * BLOCK_WIDTH - 27 + y,
				BLOCK_WIDTH * 10 + 4, BLOCK_WIDTH * 20 + 4, 5, 5);

	}

	private void drawNextBlock(Graphics2D g2d, int x, int y) {

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BLOCK_WIDTH * 11 + x, 2 * BLOCK_WIDTH - 27,
				6 * BLOCK_WIDTH, BLOCK_WIDTH * 4, 5, 5);
		g2d.setColor(Color.green);
		g2d.fillRoundRect(BLOCK_WIDTH * 11 + x, 2 * BLOCK_WIDTH - 27,
				6 * BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BLOCK_WIDTH * 11 + x, 2 * BLOCK_WIDTH - 27,
				6 * BLOCK_WIDTH, BLOCK_WIDTH * 4, 5, 5);
		g2d.drawRoundRect(BLOCK_WIDTH * 11 + x, 2 * BLOCK_WIDTH - 27,
				6 * BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);

		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		g2d.drawString("Next Block", BLOCK_WIDTH * 13 - 13 + x,
				2 * BLOCK_WIDTH - 3);

		FieldCell[][] nextBlock = game.getNextBlock().getModel();

		if (nextBlock != null) {

			for (int i = 0; i < nextBlock.length; i++) {
				for (int j = 0; j < nextBlock[i].length; j++) {
					if (nextBlock[i][j] != null) {
						g2d.setColor(getColor(nextBlock[i][j]
								.getFilledTeriminos()));
						double xOffset = (2 - (double) nextBlock[i].length / 2)
								* BLOCK_WIDTH;
						double yOffset = (1.5 - (double) nextBlock.length / 2)
								* BLOCK_WIDTH;

						g2d.fillRoundRect((int) xOffset + BLOCK_WIDTH
								* (12 + j) + x, (2 + i) * BLOCK_WIDTH
								+ (int) yOffset, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
						g2d.setStroke(new BasicStroke(2));
						g2d.setColor(Color.BLACK);
						g2d.drawRoundRect((int) xOffset + BLOCK_WIDTH
								* (12 + j) + x, (2 + i) * BLOCK_WIDTH
								+ (int) yOffset, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					}
				}
			}
		}
	}

	private void drawSaveBlock(Graphics2D g2d, int x, int y) {

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BLOCK_WIDTH * 11 + x, 5 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH * 4, 5, 5);
		g2d.setColor(Color.YELLOW);
		g2d.fillRoundRect(BLOCK_WIDTH * 11 + x, 5 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BLOCK_WIDTH * 11 + x, 5 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH * 4, 5, 5);
		g2d.drawRoundRect(BLOCK_WIDTH * 11 + x, 5 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);

		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		g2d.drawString("Stored Block", BLOCK_WIDTH * 12 + 10 + x,
				6 * BLOCK_WIDTH - 7);

		Block storedBlock = game.getStoredBlock();

		if (storedBlock != null) {
			FieldCell[][] model = storedBlock.getModel();

			for (int i = 0; i < model.length; i++) {
				for (int j = 0; j < model[i].length; j++) {
					if (model[i][j] != null) {
						g2d.setColor(getColor(model[i][j].getFilledTeriminos()));
						double xOffset = (2 - (double) model[i].length / 2)
								* BLOCK_WIDTH;
						double yOffset = (1.5 - (double) model.length / 2)
								* BLOCK_WIDTH;

						g2d.fillRoundRect((int) xOffset + BLOCK_WIDTH
								* (12 + j) + x, (6 + i) * BLOCK_WIDTH
								+ (int) yOffset, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
						g2d.setStroke(new BasicStroke(2));
						g2d.setColor(Color.BLACK);
						g2d.drawRoundRect((int) xOffset + BLOCK_WIDTH
								* (12 + j) + x, (6 + i) * BLOCK_WIDTH
								+ (int) yOffset, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					}
				}
			}
		}
	}

	private void drawScore(Graphics2D g2d, int x, int y) {

		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BLOCK_WIDTH * 11 + x, 9 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH * 2, 5, 5);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(BLOCK_WIDTH * 11 + x, 9 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BLOCK_WIDTH * 11 + x, 9 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH * 2, 5, 5);
		g2d.drawRoundRect(BLOCK_WIDTH * 11 + x, 9 * BLOCK_WIDTH,
				6 * BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);

		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		g2d.drawString("Score", BLOCK_WIDTH * 13 + 5 + x, 10 * BLOCK_WIDTH - 7);

		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));

		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int w = fm.stringWidth(Integer.toString(game.getScore()));

		g2d.drawString(Integer.toString(game.getScore()), BLOCK_WIDTH * 14 - w
				/ 2 + x, 11 * BLOCK_WIDTH - 7);

	}

	private Color getColor(Tetriminos type) {

		switch (type) {
		case I:
			return Color.CYAN;
		case J:
			return Color.BLUE;
		case L:
			return Color.ORANGE;
		case O:
			return Color.YELLOW;
		case S:
			return Color.GREEN;
		case T:
			return Color.MAGENTA;
		case Z:
			return Color.RED;
		default:
			return null;
		}
	}
}
