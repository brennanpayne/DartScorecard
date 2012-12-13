package com.brennan.dartscorecard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.brennan.dartscorecard.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void startGame(View view){
		Intent intent = null;
		switch(view.getId()){
		case R.id.hammerCricket:
			intent = new Intent(MainActivity.this, SettingsActivity.class);
			intent.putExtra("gameType","hammerCricket");
			break;
		default:
			throw new RuntimeException("Unknown button ID");
		}
		MainActivity.this.startActivity(intent);	
	}
}

