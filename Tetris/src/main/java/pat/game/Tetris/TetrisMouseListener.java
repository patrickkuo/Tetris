package pat.game.Tetris;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TetrisMouseListener implements MouseMotionListener, MouseListener,
		MouseWheelListener {

	private TetrisGUI mainFrame;

	public TetrisMouseListener(TetrisGUI mainFrame) {

		this.mainFrame = mainFrame;

	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent e) {

		if (!mainFrame.getGame().isGameEnd()) {
			int xPos = (e.getX() / 30) - 10;
			if (xPos < -3) {
				xPos = -3;
			}
			if (xPos > 6) {
				xPos = 6;
			}

			if (xPos > mainFrame.getGame().getCurrentBlock().getX()) {
				mainFrame.getGame().moveCurrentBlock(Movment.right);
			}
			if (xPos < mainFrame.getGame().getCurrentBlock().getX()) {
				mainFrame.getGame().moveCurrentBlock(Movment.left);
			}

		}
	}

	public void mouseClicked(MouseEvent e) {

		mainFrame.getGame().moveCurrentBlock(Movment.rotate);

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseWheelMoved(MouseWheelEvent e) {

		mainFrame.getGame().moveCurrentBlock(Movment.down);

	}

}
