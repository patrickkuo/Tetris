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
	private static final int BLOCK_WIDTH = 30;
	private TetrisGame game;
	private TetrisGame game2;

	public TetrisCanvas(TetrisGame game) {

		this.game = game;
		game.getPlayField();

	}

	public TetrisCanvas(TetrisGame game1, TetrisGame game2) {

		this.game = game1;
		this.game2 = game2;
		game.getPlayField();

	}

	@Override
	public void paintComponent(Graphics graphics) {

		int startPos = (game2 == null) ? 6 * BLOCK_WIDTH : 0;

		graphics.clearRect(0, 0, 850, 720);
		
		graphics.drawImage(
				blockInfo("Stored Block", Color.YELLOW, game.getStoredBlock()),
				BLOCK_WIDTH * 11 + startPos, BLOCK_WIDTH * 5, this);
		graphics.drawImage(
				blockInfo("Next Block", Color.GREEN, game.getNextBlock()),
				BLOCK_WIDTH * 11 + startPos, BLOCK_WIDTH, this);
		graphics.drawImage(drawScore(Integer.toString(game.getScore())),
				BLOCK_WIDTH * 11 + startPos, BLOCK_WIDTH *9, this);
		graphics.drawImage(playArea(game.getPlayField().getPlayField(),game.isGameEnd()),
				BLOCK_WIDTH + startPos, BLOCK_WIDTH, this);

		if (game2 != null) {
			graphics.drawImage(playArea(game2.getPlayField().getPlayField(),game2.isGameEnd()),
					BLOCK_WIDTH * 17, BLOCK_WIDTH, this);
		}
	}

	private BufferedImage playArea(List<List<FieldCell>> fieldCell,boolean gameEnd) {
		// create buffer image to draw on
		int borderWidth = 3;
		
		int imageType = (gameEnd)?BufferedImage.TYPE_BYTE_GRAY:BufferedImage.TYPE_3BYTE_BGR;
		
		BufferedImage bi = new BufferedImage(BLOCK_WIDTH * 10
				+ borderWidth * 2, BLOCK_WIDTH * 20 + borderWidth * 2, imageType);
		Graphics2D g2d = bi.createGraphics();
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw background
		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(borderWidth / 2, borderWidth / 2, BLOCK_WIDTH * 10
				+ borderWidth, BLOCK_WIDTH * 20 + borderWidth, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, BLOCK_WIDTH * 10
				+ borderWidth, BLOCK_WIDTH * 20 + borderWidth, 5, 5);

		// draw blocks

		for (List<FieldCell> column : fieldCell) {
			for (int i = 2; i < 22; i++) { // display only 20 rows
				FieldCell cell = column.get(i);
				if (cell.isFilled()) {

					g2d.setColor(getColor(cell.getFilledTeriminos()));

					int xPos = fieldCell.indexOf(column) * BLOCK_WIDTH
							+ borderWidth;
					int yPos = (column.indexOf(cell) - 2) * BLOCK_WIDTH
							+ borderWidth;

					g2d.fillRoundRect(xPos, yPos, BLOCK_WIDTH, BLOCK_WIDTH, 5,
							5);
					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Color.BLACK);
					g2d.drawRoundRect(xPos, yPos, BLOCK_WIDTH, BLOCK_WIDTH, 5,
							5);
				}
			}
		}

		return bi;

	}

	private BufferedImage blockInfo(String title, Color titleColor, Block block) {
		// create buffer image to draw on
		int borderWidth = 3;
		BufferedImage bi = (BufferedImage) createImage(6 * BLOCK_WIDTH
				+ borderWidth, BLOCK_WIDTH * 4 + borderWidth);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw frame and title frame
		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, bi.getHeight() - borderWidth, 5, 5);
		g2d.setColor(titleColor);
		g2d.fillRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, (bi.getHeight() - borderWidth) / 4, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, bi.getHeight() - borderWidth, 5, 5);
		g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, (bi.getHeight() - borderWidth) / 4, 5, 5);

		// draw title
		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int w = fm.stringWidth(title);
		g2d.drawString(title, (bi.getWidth() - w) / 2, bi.getHeight() / 5);

		// draw block
		if (block != null) {
			FieldCell[][] model = block.getModel();
			for (int i = 0; i < model.length; i++) {
				for (int j = 0; j < model[i].length; j++) {
					if (model[i][j] != null) {
						g2d.setColor(getColor(model[i][j].getFilledTeriminos()));

						double xOffset = (bi.getWidth() - model[0].length
								* BLOCK_WIDTH) / 2;
						double yOffset = (bi.getHeight() * 5 / 4 - model.length
								* BLOCK_WIDTH) / 2;

						g2d.fillRoundRect((int) xOffset + BLOCK_WIDTH * j,
								(int) yOffset + i * BLOCK_WIDTH, BLOCK_WIDTH,
								BLOCK_WIDTH, 5, 5);

						g2d.setStroke(new BasicStroke(2));
						g2d.setColor(Color.BLACK);
						g2d.drawRoundRect((int) xOffset + BLOCK_WIDTH * j,
								(int) yOffset + i * BLOCK_WIDTH, BLOCK_WIDTH,
								BLOCK_WIDTH, 5, 5);
					}
				}
			}
		}
		return bi;
	}

	private BufferedImage drawScore(String score) {

		int borderWidth = 3;
		BufferedImage bi = (BufferedImage) createImage(6 * BLOCK_WIDTH+ borderWidth, BLOCK_WIDTH * 2 + borderWidth);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw frame and title frame
		g2d.setStroke(new BasicStroke(borderWidth));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, bi.getHeight() - borderWidth, 5, 5);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, (bi.getHeight() - borderWidth) / 2, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, bi.getHeight() - borderWidth, 5, 5);
		g2d.drawRoundRect(borderWidth / 2, borderWidth / 2, bi.getWidth()
				- borderWidth, (bi.getHeight() - borderWidth) / 2, 5, 5);

		// print title
		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int w = fm.stringWidth("Score");
		g2d.drawString("Score", (bi.getWidth() - w) / 2, bi.getHeight() / 3);

		// print score
		w = fm.stringWidth(score);
		g2d.drawString(score, (bi.getWidth() - w) / 2, bi.getHeight() * 6 / 7);

		return bi;
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
