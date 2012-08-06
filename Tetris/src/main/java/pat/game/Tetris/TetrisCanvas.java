package pat.game.Tetris;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class TetrisCanvas extends Canvas{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PlayField playField;
	
	public TetrisCanvas(PlayField playField) {
		
		this.playField = playField;	
		
	}
	
	@Override
	public void paint(Graphics graphics) {
		System.out.println("called");
		Graphics2D g2 = (Graphics2D) graphics;
		
		BufferedImage bi = (BufferedImage) createImage(600, 800);
		Graphics2D g2d = bi.createGraphics();

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		
		drawGrid(g2d);
		
		
		g2.drawImage(bi, 0, 0, this);

	}
	
	private void drawGrid(Graphics2D g2d){
		
		FieldCell[][] fieldCell = playField.getPlayField();
		
		for (int i = 0 ; i<fieldCell.length;i++){
			for (int j=0; j<fieldCell[i].length; j++){
				if(fieldCell[i][j].isFilled()){
				g2d.setColor(Color.BLUE);
				g2d.fillRect(j*10+10, i*10+10, 10, 10);
				}
			}
		}
	}

}
