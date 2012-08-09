package pat.game.Tetris;

import java.io.Serializable;

public class ScoreItem implements Serializable,Comparable<ScoreItem>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;
	
	public ScoreItem(int score, String name) {
		this.score = score;
		this.setName(name);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name+"    "+score;
		
	}

	@Override
	public int compareTo(ScoreItem o) {
		return (this.score<o.score)?-1:(this.score>o.score)?1:0;
	}


}
