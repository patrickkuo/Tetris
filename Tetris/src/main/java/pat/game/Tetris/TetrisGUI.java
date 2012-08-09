package pat.game.Tetris;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class TetrisGUI extends JFrame {

	private static final int FRAME_WIDTH = 850;
	private static final int FRAME_HEIGHT = 720;
	private TetrisGame game;
	private PlayField game2;
	private GameThread gameThread;
	private Thread repaintThread;
	private TetrisCanvas tC;
	private JMenuItem stopResume;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TetrisGUI(TetrisGame game) {
		super("Tetris");
		
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
		stopResume = new JMenuItem("Resume");
		stopResume.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				toggle();
				
			}
		});

		gameMenu.add(singlePlayer);
		gameMenu.add(stopResume);
		menuBar.add(gameMenu);
		setJMenuBar(menuBar);

		// canvas class handle drawings
		tC = new TetrisCanvas(game);
		tC.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		this.add(tC);
		game.setCanvas(tC);

		// game thread
		gameThread = new GameThread(game, stopResume,this);
		gameThread.start();

		// thread for repaint
		repaintThread = new Thread(new RepaintRunner(tC));
		repaintThread.start();

		this.addKeyListener(new TetrisKeyListener(this));
		this.addMouseMotionListener(new TetrisMouseListener(this));
		this.addMouseListener(new TetrisMouseListener(this));
		this.addMouseWheelListener(new TetrisMouseListener(this));
		this.addFocusListener(new TetrisFocusListener(this));

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
		gameThread = new GameThread(game,stopResume,this);
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

	public GameThread getGameThread() {
		return gameThread;
	}

	public Thread getRepaintThread() {
		return repaintThread;
	}
	
	private void toggle(){
		gameThread.toggle();
	}

	public void scorePopup(final int score) {
		
		final JDialog popUp = new JDialog(this);
		popUp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int frameWidth = 270;
		int frameHeight = 100;
		int frameLocationX = (int) ((dim.getWidth() - frameWidth) / 2);
		int frameLocationY = (int) ((dim.getHeight() - frameHeight) / 2);

		popUp.setBounds(frameLocationX, frameLocationY, frameWidth, frameHeight);
		popUp.setLayout(null);
		
		JLabel lable = new JLabel(" New High Score! Please enter your name.");
		lable.setBounds(10, 0, 250, 30);
		
		final JTextField tf = new JTextField();
		tf.setBounds(15,35,130,20);
		
		JButton submit = new JButton("Submit");
		submit.setBounds(160, 35, 80, 20);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveScore(new ScoreItem(score, tf.getText()));
				popUp.dispose();
			}
		});;
		
		popUp.add(lable);
		popUp.add(tf);
		popUp.add(submit);
		
		popUp.setVisible(true);
		popUp.setResizable(false);
		
	}
	
	private void saveScore(ScoreItem score){
		
		game.getScoreList().add(score);
		Collections.reverse(game.getScoreList());
		synchronized (tC) {
			
			tC.notify();
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("score.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game.getScoreList());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
