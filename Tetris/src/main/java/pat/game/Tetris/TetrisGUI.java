package pat.game.Tetris;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class TetrisGUI extends JFrame {

	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 720;
	private TetrisGame game;
	private PlayField game2;
	private Thread gameThread;
	private Thread repaintThread;
	private TetrisCanvas tC;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TetrisGUI(TetrisGame game) {
		super("Tetris");
		

		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip = addr.getHostAddress();
			System.out.println(ip);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		JMenuItem singlePlayer = new JMenuItem("New Game");

		singlePlayer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				newGame();
				start();
			}
		});

		gameMenu.add(singlePlayer);
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);

		// canvas class handle drawings
		tC = new TetrisCanvas(game);
		tC.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		this.add(tC);
		game.setCanvas(tC);

		// game thread
		gameThread = new Thread(new GameRunner(game));
		gameThread.start();

		// thread for repaint
		repaintThread = new Thread(new RepaintRunner(tC));
		repaintThread.start();

		this.addKeyListener(new TetrisKeyListener(this));
		this.addMouseMotionListener(new TetrisMouseMotionListener(this));
		this.addMouseListener(new TetrisMouseMotionListener(this));
		this.addMouseWheelListener(new TetrisMouseMotionListener(this));
		// set visible
		this.setVisible(true);
		this.setResizable(false);
		
		
	}

	public void newGame() {

		this.game = new TetrisGame();
		this.remove(tC);
		tC = new TetrisCanvas(game);
		tC.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

		this.add(tC);
		game.setCanvas(tC);
		
		// kill old thread
		gameThread.interrupt();
		repaintThread.interrupt();
		
		// thread for repaint
		repaintThread = new Thread(new RepaintRunner(tC));
		
		// game thread
		gameThread = new Thread(new GameRunner(game));
	}

	public void start() {
		
		repaintThread.start();
		gameThread.start();

	}


	public TetrisGame getGame() {
		return game;
	}

	public PlayField getGame2() {
		return game2;
	}

	public void setGame2(PlayField game2) {
		this.game2 = game2;
	}

	public Thread getGameThread() {
		return gameThread;
	}

	public Thread getRepaintThread() {
		return repaintThread;
	}

}
