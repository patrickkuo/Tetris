package pat.game.Tetris;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TetrisGUI extends JFrame {

	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 720;
	private TetrisGame game;
	private Thread gameThread;
	private Thread repaintThread;
	private TetrisCanvas tC;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TetrisGUI(TetrisGame game) {
		super("Teris");
		this.game = game;
		// set exit action
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set window size and location
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = FRAME_WIDTH;
		int frameHeight = FRAME_HEIGHT;
		int frameLocationX = (int) ((dim.getWidth() - frameWidth) / 2);
		int frameLocationY = (int) ((dim.getHeight() - frameHeight) / 2);

		this.setBounds(frameLocationX, frameLocationY, frameWidth, frameHeight);

		// add menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu gameMenu = new JMenu("Game");
		JMenuItem singlePlayer = new JMenuItem("Single Player");
		JMenuItem multiplayer = new JMenuItem("Multiplayer");

		singlePlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});

		gameMenu.add(singlePlayer);
		gameMenu.add(multiplayer);
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);

		// canvas class handle drawings
		tC = new TetrisCanvas(game, game);
		tC.setBounds(2, 2, 850, 700);
		this.add(tC);
		game.setCanvas(tC);

		// game thread
		gameThread = new Thread(new GameRunner(game));
		gameThread.start();

		// thread for repaint
		repaintThread = new Thread(new RepaintRunner(tC));
		repaintThread.start();

		this.addKeyListener(new TetrisKeyListener(game));
		tC.addKeyListener(new TetrisKeyListener(game));

		// set visible
		this.setVisible(true);
		this.setResizable(false);
	}

	private void newGame() {

		this.removeKeyListener(this.getKeyListeners()[0]);
		this.game = new TetrisGame();
		this.remove(tC);
		tC = new TetrisCanvas(game);
		tC.setBounds(2, 2, 850, 700);
		this.addKeyListener(new TetrisKeyListener(game));
		tC.addKeyListener(new TetrisKeyListener(game));
		this.add(tC);

		game.setCanvas(tC);
		gameThread.interrupt();
		repaintThread.interrupt();
		repaintThread = new Thread(new RepaintRunner(tC));
		repaintThread.start();
		gameThread = new Thread(new GameRunner(game));
		gameThread.start();
	}
}
