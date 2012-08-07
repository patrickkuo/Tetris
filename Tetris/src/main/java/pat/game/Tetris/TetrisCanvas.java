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
	private static final int BLOCK_WIDTH = 30;
	private BufferedImage bi;
	private Graphics2D g2d;
	private TetrisGame game;

	public TetrisCanvas(TetrisGame game) {

		this.game = game;
		this.playField = game.getPlayField();

	}

	@Override
	public void paintComponent(Graphics graphics) {

		// Graphics2D g2 = (Graphics2D) graphics;

		if (this.bi == null) {
			this.bi = (BufferedImage) createImage(800, 700);
		}

		if (this.g2d == null) {
			this.g2d = bi.createGraphics();

			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}

		g2d.clearRect(0, 0, 800, 700);
		drawNextBlock(g2d);
		drawBackGround(g2d);
		drawGrid(g2d);

		graphics.drawImage(bi, 0, 0, this);

	}

	private void drawGrid(Graphics2D g2d) {

		List<List<FieldCell>> fieldCell = playField.getPlayField();

		for (List<FieldCell> column : fieldCell) {
			for (int i = 2; i < 22; i++) { // display only 20 rows
				FieldCell cell = column.get(i);
				if (cell.isFilled()) {
					
					g2d.setColor(getColor(cell.getFilledTeriminos()));

					g2d.fillRoundRect(fieldCell.indexOf(column) * BLOCK_WIDTH
							+ 35, column.indexOf(cell) * BLOCK_WIDTH - 25,
							BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(fieldCell.indexOf(column) * BLOCK_WIDTH
							+ 35, column.indexOf(cell) * BLOCK_WIDTH - 25,
							BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
				}
			}
		}
	}

	private void drawBackGround(Graphics2D g2d) {

		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(Color.WHITE);

		g2d.fillRoundRect(33, 2 * BLOCK_WIDTH - 27, BLOCK_WIDTH * 10 + 4,
				BLOCK_WIDTH * 20 + 4, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(33, 2 * BLOCK_WIDTH - 27, BLOCK_WIDTH * 10 + 4,
				BLOCK_WIDTH * 20 + 4, 5, 5);

	}

	private void drawNextBlock(Graphics2D g2d) {

		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(5 + BLOCK_WIDTH * 11, 2 * BLOCK_WIDTH - 27, 170, 170,
				5, 5);
		g2d.setColor(Color.green);
		g2d.fillRoundRect(5 + BLOCK_WIDTH * 11, 2 * BLOCK_WIDTH - 27, 170, 40,
				5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(5 + BLOCK_WIDTH * 11, 2 * BLOCK_WIDTH - 27, 170, 170,
				5, 5);
		g2d.drawRoundRect(5 + BLOCK_WIDTH * 11, 2 * BLOCK_WIDTH - 27, 170, 40,
				5, 5);

		FieldCell[][] nextBlock = game.getNextBlock().getModel();

		if(nextBlock!=null){
		
			System.out.println(nextBlock.length +";"+ nextBlock[0].length);
			
		for (int i = 0; i < nextBlock.length; i++) {
			for (int j = 0; j < nextBlock[i].length; j++) {
				if (nextBlock[i][j] != null) {
					g2d.setColor(getColor(nextBlock[i][j].getFilledTeriminos()));
					
					g2d.fillRoundRect(BLOCK_WIDTH*(nextBlock[i].length/3) + BLOCK_WIDTH * (11+j), (2+i) * BLOCK_WIDTH +45,BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(BLOCK_WIDTH*(nextBlock[i].length/3) + BLOCK_WIDTH * (11+j), (2+i) * BLOCK_WIDTH+45,BLOCK_WIDTH, BLOCK_WIDTH, 5, 5);
				}
			}
		}
		}
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
