package com.brennan.dartscorecard;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

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
		setContentView(R.layout.activity_hammer_game);
		RelativeLayout mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
		
		for(int i = 0; i < players.size(); i++){
			Log.v(TAG, "Player " + i + ": " + players.get(i).getName());
			FrameLayout f = new FrameLayout(this);
			
			View v = new View(this);
			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, 10);
			
			v.setBackgroundColor(Color.BLUE);
			v.setLayoutParams(params);
			
			View v2 = new View(this);
			LayoutParams params2 = new LayoutParams(10, LayoutParams.MATCH_PARENT);
			v2.setBackgroundColor(Color.GREEN);
			v2.setLayoutParams(params2);
			
			f.addView(v);
			f.addView(v2);
			
			FrameLayout.LayoutParams fParams = new FrameLayout.LayoutParams(100, 100);
			f.setLayoutParams(fParams);
			
			RelativeLayout.LayoutParams rParams = new RelativeLayout.LayoutParams(200, 100);
			rParams.addRule(RelativeLayout.BELOW, R.id.textView1);
			
			mRelativeLayout.addView(f, rParams);
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hammer_game, menu);
		return true;
	}

}
