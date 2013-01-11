package com.brennan.gamelogic;

import java.util.ArrayList;



abstract class Game {

	private ArrayList<Player> players;
	private ArrayList<Integer> marks;
	
	public Game(){
		players = new ArrayList<Player>();
		marks = new ArrayList<Integer>();
	}


	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
	public void addPlayer(Player p){
		players.add(p);
	}
	
	public void addPlayers(ArrayList<Player> players){
		for(int i = 0; i < players.size(); i++){
			addPlayer(players.get(i));
		}
	}


	public ArrayList<Integer> getMarks() {
		return marks;
	}


	public void setMarks(ArrayList<Integer> marks) {
		this.marks = marks;
	}

}
