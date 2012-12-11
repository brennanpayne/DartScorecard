package com.brennan.gamelogic;

public class Player {
	private int score;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	public Player(String name){
		this.name = name;
	}

}
