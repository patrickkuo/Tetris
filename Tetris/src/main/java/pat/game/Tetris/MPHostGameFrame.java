package pat.game.Tetris;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class MPHostGameFrame extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MPHostGameFrame(TetrisGUI mainFrame) {
		
		mainFrame.getGameThread().interrupt();
		mainFrame.getRepaintThread().interrupt();
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int frameLocationX = (int) ((dim.getWidth() - 200) / 2);
		int frameLocationY = (int) ((dim.getHeight() - 200) / 2);

		setBounds(frameLocationX, frameLocationY, 200, 200);
		
		setVisible(true);
		
		Thread recieveThread = new Thread(new Reciever(mainFrame,1234));
		recieveThread.start();
	
	}
	

}
