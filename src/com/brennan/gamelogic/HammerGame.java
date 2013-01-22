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
		marks.add(20);
		marks.add(19);
		marks.add(18);
		Integer temp1 = rng.nextInt(9) + 13;
		if(temp1 == 21)
			temp1 = 25;
		marks.add(temp1);
		marks.add(17);
		marks.add(16);
		marks.add(15);
		Integer temp2 = rng.nextInt(9) + 13;
		if(temp2 == 21)
			temp2 = 25;
		marks.add(temp2);
		
		super.setMarks(marks);
	}
}
