package com.example.dartscorecard;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.RadioGroup;

public class GameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_game, menu);
		return true;
	}
	
	View.OnClickListener startHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			RadioGroup g = (RadioGroup) findViewById(R.id.rPlayerGroup);
			int numPlayers = -1;
			
			switch(g.getCheckedRadioButtonId()){
				case 1: numPlayers = 2;
						break;
				case 2: numPlayers = 3;
						break;
				case 3: numPlayers = 4;
						break;
			}
			
		}
	};
}
