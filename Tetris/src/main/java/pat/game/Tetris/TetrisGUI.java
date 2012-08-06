package pat.game.Tetris;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class TetrisGUI extends JFrame {

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 800;
	private TetrisGame game;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TetrisGUI(TetrisGame game) {
		super("Teris");
		this.game = game;
		System.out.println(game.getPlayField());	
		
		// set exit action
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set window size and location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = FRAME_WIDTH;
		int frameHeight = FRAME_HEIGHT;
		int frameLocationX = (int) ((dim.getWidth() - frameWidth) / 2);
		int frameLocationY = (int) ((dim.getHeight() - frameHeight) / 2);

		this.setBounds(frameLocationX, frameLocationY, frameWidth, frameHeight);
		
		// canvas class handle drawings
		final TetrisCanvas tC = new TetrisCanvas(game.getPlayField());
		tC.setBounds(2, 2, 600, 800);
		this.add(tC);
		
		// thread for repaint
		Thread repaintThread = new Thread(new RepaintRunner(tC));
		repaintThread.start();
		
		this.addKeyListener(new TetrisKeyListener(game));
		
		// set visible
		this.setVisible(true);
		this.setResizable(false);
	}

}
