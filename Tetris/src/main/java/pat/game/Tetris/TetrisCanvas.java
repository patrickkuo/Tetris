package pat.game.Tetris;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class TetrisCanvas extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayField playField;
	private static final int BLOCK_WIDTH = 35;
	private BufferedImage bi;
	private Graphics2D g2d;

	public TetrisCanvas(PlayField playField) {

		this.playField = playField;

	}

	@Override
	public void paintComponent(Graphics graphics) {

		// Graphics2D g2 = (Graphics2D) graphics;

		if (this.bi == null) {
			this.bi = (BufferedImage) createImage(600, 800);
		}

		if (this.g2d == null) {
			this.g2d = bi.createGraphics();

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		g2d.clearRect(0, 0, 600, 800);
		drawBackGround(g2d);
		drawGrid(g2d);

		graphics.drawImage(bi, 0, 0, this);

	}

	private void drawGrid(Graphics2D g2d) {

		List<List<FieldCell>>  fieldCell = playField.getPlayField();

		for (List<FieldCell> column:fieldCell) {
			for (int i = 2; i<22;i++) {    // display only 20 rows
				FieldCell cell = column.get(i);
				if (cell.isFilled()) {

					switch (cell.getFilledTeriminos()) {
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
					g2d.fillRoundRect(fieldCell.indexOf(column) * BLOCK_WIDTH + 35, column.indexOf(cell) * BLOCK_WIDTH
							- 35, BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(fieldCell.indexOf(column) * BLOCK_WIDTH + 35, column.indexOf(cell) * BLOCK_WIDTH
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
