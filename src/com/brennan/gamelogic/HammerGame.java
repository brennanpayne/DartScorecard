package com.brennan.gamelogic;

import java.util.ArrayList;
import java.util.Random;

public class HammerGame extends Game{

	public HammerGame(){
		super();
		generateMarks();
	}
	
	private void generateMarks(){
		ArrayList<Integer> marks = new ArrayList<Integer>();
		Random rng = new Random();

		//Add rounds with randoms
		marks.add(rng.nextInt(9) + 13);
		marks.add(15);
		marks.add(16);
		marks.add(17);
		marks.add(rng.nextInt(9) + 13);
		marks.add(18);
		marks.add(19);
		marks.add(20);
		
		super.setMarks(marks);
	}
}
