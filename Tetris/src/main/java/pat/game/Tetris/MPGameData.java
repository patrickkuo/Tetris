package pat.game.Tetris;

import java.io.Serializable;

public class MPGameData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PlayField playField;
	private String address;
	
	public MPGameData(PlayField playField, String address) {
		this.playField = playField;
		this.address = address;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public PlayField getPlayField() {
		return playField;
	}
	public void setPlayField(PlayField playField) {
		this.playField = playField;
	}
	
	

}
