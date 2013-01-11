package com.brennan.dartscorecard;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import com.brennan.dartscorecard.R;
import com.brennan.gamelogic.HammerGame;
import com.brennan.gamelogic.Player;

public class HammerGameActivity extends Activity {
	
	
	private static final String TAG = "HammerGameActivity";
	ArrayList<Player> players;
	HammerGame game;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Retrieve extras
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			players = extras.getParcelableArrayList("players");
		}
		
		Log.v(TAG,"Recived players in intent");
		
		game = new HammerGame();
		game.addPlayers(players);
		
		for(int i = 0; i < players.size(); i++)
			Log.v(TAG, "Player " + i + ": " + players.get(i).getName());
		
		
		setContentView(R.layout.activity_hammer_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hammer_game, menu);
		return true;
	}

}
