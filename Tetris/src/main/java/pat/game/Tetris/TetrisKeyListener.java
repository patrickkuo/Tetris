package pat.game.Tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {
	
	private TetrisGame game;

	public TetrisKeyListener(TetrisGame game){
		
		this.game = game;
		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			game.moveCurrentBlock(Movment.down);
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE){
			game.moveCurrentBlock(Movment.rotate);
		}
		
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent e) {
		
		
	}

}
