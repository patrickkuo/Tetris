package pat.game.Tetris;

import java.io.Serializable;

public class FieldCell implements Serializable{
	
	private boolean filled;
	private Tetriminos filledTeriminos;
	
	public FieldCell(){
		this.filled = false;
		this.filledTeriminos = null;
	}
	
	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public Tetriminos getFilledTeriminos() {
		return filledTeriminos;
	}

	public void setFilledTeriminos(Tetriminos filledTeriminos) {
		this.filledTeriminos = filledTeriminos;
	}
	
	public void empty(){
		this.filled = false;
		this.filledTeriminos = null;
	}
	public void fill(Tetriminos type){
		this.filled = true;
		this.filledTeriminos = type;
	}



}
