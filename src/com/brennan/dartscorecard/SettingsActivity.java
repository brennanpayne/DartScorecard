package com.brennan.dartscorecard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.brennan.dartscorecard.R;
import com.brennan.gamelogic.HammerGame;
import com.brennan.gamelogic.Player;

public class SettingsActivity extends Activity {
	private static final String TAG = "GameActivity"; 
	Button startButton;
	private String gameType;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			gameType = extras.getString("gameType");
		}
		setContentView(R.layout.activity_settings);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(startHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_settings, menu);
		return true;
	}
	
	 /* 
	 * This is triggered after the number of users has been selected
	 * On return it starts this same intent with new params (number of player)
	 */
	View.OnClickListener startHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
						
			RadioGroup g = (RadioGroup) findViewById(R.id.rPlayerGroup);
			int numPlayers = -1;
			switch(g.getCheckedRadioButtonId()){
				case R.id.twoPlayer: numPlayers = 2;
						break;
				case R.id.threePlayer: numPlayers = 3;
						break;
				case R.id.fourPlayer: numPlayers = 4;
						break;
			}
			
			Log.i(TAG, "Creating " + numPlayers + " players");
			
			HammerGame game = new HammerGame();
			for(int i = 0; i < numPlayers; i++){
				Player p = new Player("Player " + (i+1));
				game.addPlayer(p);
			}
			
			Log.i(TAG, "Created players and added them to game");
			Intent intent = new Intent(getBaseContext(), HammerGameActivity.class);
			intent.putExtra("numPlayers", numPlayers);
			intent.putExtra("gameType", gameType);
			startActivity(intent);
		}
	};
}
