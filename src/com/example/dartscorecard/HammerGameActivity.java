package com.example.dartscorecard;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import com.brennan.dartscorecard.R;

public class HammerGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hammer_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hammer_game, menu);
		return true;
	}

}
