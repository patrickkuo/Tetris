package pat.game.Tetris;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class TetrisCanvas extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayField playField;
	private static final int BLOCK_WIDTH = 35;

	public TetrisCanvas(PlayField playField) {

		this.playField = playField;

	}

	@Override
	public void paint(Graphics graphics) {

		Graphics2D g2 = (Graphics2D) graphics;

		BufferedImage bi = (BufferedImage) createImage(600, 800);
		Graphics2D g2d = bi.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		drawBackGround(g2d);
		drawGrid(g2d);

		g2.drawImage(bi, 0, 0, this);

	}

	private void drawGrid(Graphics2D g2d) {

		FieldCell[][] fieldCell = playField.getPlayField();

		for (int i = 2; i < fieldCell.length; i++) {
			for (int j = 0; j < fieldCell[i].length; j++) {
				if (fieldCell[i][j].isFilled()) {

					switch (fieldCell[i][j].getFilledTeriminos()) {
					case I:
						g2d.setColor(Color.CYAN);
						break;
					case J:
						g2d.setColor(Color.BLUE);
						break;
					case L:
						g2d.setColor(Color.ORANGE);
						break;
					case O:
						g2d.setColor(Color.YELLOW);
						break;
					case S:
						g2d.setColor(Color.GREEN);
						break;
					case T:
						g2d.setColor(Color.MAGENTA);
						break;
					case Z:
						g2d.setColor(Color.RED);
						break;
					default:
						break;
					}
					g2d.fillRoundRect(j * BLOCK_WIDTH + 35, i * BLOCK_WIDTH
							- 35, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(j * BLOCK_WIDTH + 35, i * BLOCK_WIDTH
							- 35, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
				}
			}
		}
	}

	private void drawBackGround(Graphics2D g2d) {

		g2d.setStroke(new BasicStroke(6));
		g2d.setColor(Color.WHITE);

		g2d.fillRoundRect(33, 2 * BLOCK_WIDTH - 37, BLOCK_WIDTH * 10 + 4,
				BLOCK_WIDTH * 20 + 4, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(33, 2 * BLOCK_WIDTH - 37, BLOCK_WIDTH * 10 + 4,
				BLOCK_WIDTH * 20 + 4, 5, 5);

	}

}
