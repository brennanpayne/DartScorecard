package com.example.dartscorecard;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class GameActivity extends Activity {
	private static final String TAG = "GameActivity"; 
	Button startButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		startButton = (Button) findViewById(R.id.startButton);
		startButton.setOnClickListener(startHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}
	
	// This is triggered after the number of users has been selected
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
			Log.i(TAG, "numPlayers: " + numPlayers);
		}
	};
}
