package com.brennan.gamelogic;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	private ArrayList<Player> players;
	private ArrayList<Integer> rounds;
	
	public Game(int numPlayers){
		setPlayers(new ArrayList<Player>());
		setRounds(generateRound());
	}
	
	private ArrayList<Integer> generateRound(){
		ArrayList<Integer> rounds = new ArrayList<Integer>();
		Random rng = new Random();

		//Add rounds with randoms
		rounds.add(rng.nextInt(21) + 1);
		rounds.add(15);
		rounds.add(16);
		rounds.add(17);
		rounds.add(rng.nextInt(21) + 1);
		rounds.add(18);
		rounds.add(19);
		rounds.add(20);
		
		return rounds;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public ArrayList<Integer> getRounds() {
		return rounds;
	}

	
}
