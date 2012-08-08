package pat.game.Tetris;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class TetrisMouseMotionListener implements MouseMotionListener,MouseListener,MouseWheelListener{

	private TetrisGame game;

	public TetrisMouseMotionListener(TetrisGame game){
		
		this.game = game;
		
	}
	
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {

		int xPos = (e.getX()/30)-10;
		if (xPos<-3){
			xPos=-3;
		}
		if(xPos>6){
			xPos=6;
		}		
		
		if(xPos>game.getCurrentBlock().getX()){
			game.moveCurrentBlock(Movment.right);
		}
		if(xPos<game.getCurrentBlock().getX()){
			game.moveCurrentBlock(Movment.left);
		}

		
	}

	public void mouseClicked(MouseEvent e) {
		
		game.moveCurrentBlock(Movment.rotate);
		
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

		game.moveCurrentBlock(Movment.down);
		
	}

}
