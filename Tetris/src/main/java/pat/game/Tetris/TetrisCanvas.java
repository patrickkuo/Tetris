package pat.game.Tetris;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.RGBColor;

public class TetrisCanvas extends JPanel {

	/**
	 * 
	 */
	private static final int BLOCK_WIDTH = 30;
	private static final int FRAME_WIDTH = BLOCK_WIDTH*20;
	private static final int FRAME_HEIGHT = BLOCK_WIDTH*24;
	private static final int BORDER_WIDTH = 3;
	private static final long serialVersionUID = 1L;
	private TetrisGUI mainFrame;

	public TetrisCanvas(TetrisGUI mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void paintComponent(Graphics graphics) {
		
		TetrisGame game = mainFrame.getGame();

		graphics.clearRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		graphics.drawImage(
				blockInfo("Stored Block", Color.YELLOW, game.getStoredBlock()),
				BLOCK_WIDTH * 12 -BORDER_WIDTH, BLOCK_WIDTH * 5, this);
		graphics.drawImage(
				blockInfo("Next Block", Color.GREEN, game.getNextBlock()),
				BLOCK_WIDTH * 12 -BORDER_WIDTH, BLOCK_WIDTH, this);
		graphics.drawImage(drawScore(Integer.toString(game.getScore())),
				BLOCK_WIDTH * 12 -BORDER_WIDTH, BLOCK_WIDTH * 9, this);

		graphics.drawImage(printHighScore(), BLOCK_WIDTH * 12 -BORDER_WIDTH,
				BLOCK_WIDTH * 14, this);

		graphics.drawImage(
				playArea(game.getPlayField().getPlayField(), game.isGameEnd()),
				BLOCK_WIDTH*2 -BORDER_WIDTH*2, BLOCK_WIDTH, this);
	}

	private BufferedImage playArea(List<List<FieldCell>> fieldCell,
			boolean gameEnd) {
		// create buffer image to draw on

		int imageType = (gameEnd) ? BufferedImage.TYPE_BYTE_GRAY
				: BufferedImage.TYPE_4BYTE_ABGR;

		BufferedImage bi = new BufferedImage(
				BLOCK_WIDTH * 10 + BORDER_WIDTH * 2, BLOCK_WIDTH * 20
						+ BORDER_WIDTH * 2, imageType);
		Graphics2D g2d = bi.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw background
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, BLOCK_WIDTH * 10
				+ BORDER_WIDTH, BLOCK_WIDTH * 20 + BORDER_WIDTH, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, BLOCK_WIDTH * 10
				+ BORDER_WIDTH, BLOCK_WIDTH * 20 + BORDER_WIDTH, 5, 5);

		// draw blocks
		if (fieldCell != null) {
			for (List<FieldCell> column : fieldCell) {
				for (int i = 2; i < 22; i++) { // display only 20 rows
					FieldCell cell = column.get(i);
					if (cell.isFilled()) {



						int xPos = fieldCell.indexOf(column) * BLOCK_WIDTH
								+ BORDER_WIDTH;
						int yPos = (column.indexOf(cell) - 2) * BLOCK_WIDTH
								+ BORDER_WIDTH;
						
						g2d.drawImage(drawSquare(cell),xPos,yPos,null);
						g2d.setStroke(new BasicStroke(2));
						g2d.setColor(Color.BLACK);
						g2d.drawRoundRect(xPos, yPos, BLOCK_WIDTH, BLOCK_WIDTH,5,5);
					}
				}
			}
		}
		return bi;

	}
	
	private BufferedImage drawSquare(FieldCell cell){
		 
		BufferedImage bi = (BufferedImage) createImage(BLOCK_WIDTH,BLOCK_WIDTH);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setBackground(null);
		
		GradientPaint gp = new GradientPaint(BLOCK_WIDTH/2, BLOCK_WIDTH/2,getColor(cell.getFilledTeriminos()) , 0, 0, Color.WHITE);
		
		//g2d.setColor(getColor(cell.getFilledTeriminos()));
		
		g2d.setPaint(gp);
		
		g2d.fillRoundRect(BORDER_WIDTH/2, BORDER_WIDTH/2, BLOCK_WIDTH, BLOCK_WIDTH,
				5, 5);

		
		return bi;
		
		
	}
	

	private BufferedImage blockInfo(String title, Color titleColor, Block block) {
		// create buffer image to draw on
		BufferedImage bi = (BufferedImage) createImage(6 * BLOCK_WIDTH
				+ BORDER_WIDTH, BLOCK_WIDTH * 4 + BORDER_WIDTH);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw frame and title frame
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, bi.getHeight() - BORDER_WIDTH, 5, 5);
		
		GradientPaint gp = new GradientPaint(0,0,Color.WHITE,0,bi.getHeight()/4,titleColor);
		g2d.setPaint(gp);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, (bi.getHeight() - BORDER_WIDTH) / 4, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, bi.getHeight() - BORDER_WIDTH, 5, 5);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, (bi.getHeight() - BORDER_WIDTH) / 4, 5, 5);

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

						double xOffset = (bi.getWidth() - model[0].length
								* BLOCK_WIDTH) / 2;
						double yOffset = (bi.getHeight() * 5 / 4 - model.length
								* BLOCK_WIDTH) / 2;
						
						g2d.drawImage(drawSquare(model[i][j]),(int) xOffset + BLOCK_WIDTH * j,
								(int) yOffset + i * BLOCK_WIDTH,null);

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
		
		BufferedImage bi = (BufferedImage) createImage(6 * BLOCK_WIDTH
				+ BORDER_WIDTH, BLOCK_WIDTH * 2 + BORDER_WIDTH);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw frame and title frame
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, bi.getHeight() - BORDER_WIDTH, 5, 5);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, (bi.getHeight() - BORDER_WIDTH) / 2, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, bi.getHeight() - BORDER_WIDTH, 5, 5);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, (bi.getHeight() - BORDER_WIDTH) / 2, 5, 5);

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

	private BufferedImage printHighScore() {

		BufferedImage bi = (BufferedImage) createImage(6 * BLOCK_WIDTH
				+ BORDER_WIDTH, BLOCK_WIDTH * 7 + BORDER_WIDTH + BORDER_WIDTH);
		Graphics2D g2d = bi.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		// draw frame and title frame
		g2d.setStroke(new BasicStroke(BORDER_WIDTH));
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, bi.getHeight() - BORDER_WIDTH, 5, 5);
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, (bi.getHeight() - BORDER_WIDTH) / 7, 5, 5);
		g2d.setColor(Color.BLACK);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, bi.getHeight() - BORDER_WIDTH, 5, 5);
		g2d.drawRoundRect(BORDER_WIDTH / 2, BORDER_WIDTH / 2, bi.getWidth()
				- BORDER_WIDTH, (bi.getHeight() - BORDER_WIDTH) / 7, 5, 5);

		// print title
		g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		FontMetrics fm = g2d.getFontMetrics(g2d.getFont());
		int w = fm.stringWidth("High Scores");
		g2d.drawString("High Scores", (bi.getWidth() - w) / 2,
				bi.getHeight() / 10);

		List<?> list = mainFrame.getGame().getScoreList();

		for (int i = 0; i < list.size(); i++) {

			if (i < 3) {
				g2d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
			} else {
				g2d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

			}
			
			ScoreItem si = (ScoreItem) list.get(i);

			fm = g2d.getFontMetrics(g2d.getFont());
			w = fm.stringWidth((i + 1) + ".");
			g2d.drawString((i + 1) + ".", (bi.getWidth() / 5 - w) / 2,
					(bi.getHeight() / 12) * (i + 3) - 5);

			w = fm.stringWidth(si.getName());
			g2d.drawString(si.getName(), (bi.getWidth() * 2 / 5 - w)
					/ 2 + bi.getWidth() / 5, (bi.getHeight() / 12) * (i + 3)
					- 5);

			w = fm.stringWidth(si.getScore() + "");
			g2d.drawString(si.getScore() + "",
					(bi.getWidth() * 2 / 5 - w) / 2 + bi.getWidth() * 3 / 5,
					(bi.getHeight() / 12) * (i + 3) - 5);

		}

		return bi;

	}

	private Color getColor(Tetriminos type) {

		if (type == null) {
			return Color.GRAY;
		}

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
			return Color.GRAY;

		}
	}
}
