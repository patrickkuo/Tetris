package pat.game.Tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {

	private TetrisGUI mainFrame;

	public TetrisKeyListener(TetrisGUI mainFrame) {

		this.mainFrame = mainFrame;

	}

	public void keyPressed(KeyEvent e) {
		if (!mainFrame.getGame().isGameEnd()) {
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				mainFrame.getGame().moveCurrentBlock(Movment.down);
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				mainFrame.getGame().moveCurrentBlock(Movment.left);

			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				mainFrame.getGame().moveCurrentBlock(Movment.right);

			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				mainFrame.getGame().moveCurrentBlock(Movment.rotate);
			}
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {

				mainFrame.getGame().saveBlock();

			}
			if (mainFrame.getGameThread().isPause()) {
				mainFrame.getGameThread().resumeThread();
			}
		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent e) {

	}

}
