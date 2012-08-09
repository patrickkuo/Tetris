package pat.game.Tetris;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TetrisFocusListener implements FocusListener {

	private TetrisGUI mainFrame;

	public TetrisFocusListener(TetrisGUI mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub

	}

	public void focusLost(FocusEvent e) {

		mainFrame.getGameThread().pauseThread();

	}

}
