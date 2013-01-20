package com.brennan.dartscorecard;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brennan.dartscorecard.R;
import com.brennan.gamelogic.HammerGame;
import com.brennan.gamelogic.Player;

public class HammerGameActivity extends Activity {


	private static final String TAG = "HammerGameActivity";
	private TextView round_mark, dart2_text, dart3_text;
	private RelativeLayout mRelativeLayout;
	ArrayList<Player> players;
	private ArrayList<String> playerNames;
	private int numTurns;
	private Button nextTurnButton, prevTurnButton;
	HammerGame game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Retrieve extras
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			players = extras.getParcelableArrayList("players");
		}

		Log.v(TAG,"Recived players in Hammer Game");
		playerNames = new ArrayList<String>();
		
		for(int i = 0; i < players.size(); i++)
			playerNames.add(players.get(i).getName());
		

		game = new HammerGame();
		game.addPlayers(players);
		numTurns = 0;
		setContentView(R.layout.activity_hammer_game);

		
		mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
		round_mark = (TextView) findViewById(R.id.round_mark);
		dart2_text = (TextView) findViewById(R.id.dart2_text);
		dart3_text = (TextView) findViewById(R.id.dart3_text);

		nextTurnButton = (Button) findViewById(R.id.next_turn_button);
		nextTurnButton.setOnClickListener(nextTurnHandler);

		prevTurnButton = (Button) findViewById(R.id.prev_turn_button);
		prevTurnButton.setEnabled(false);
		prevTurnButton.setOnClickListener(prevTurnHandler);
		
		
	}

	/*
	 * Goes to the next turn or round
	 */
	View.OnClickListener nextTurnHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			numTurns++;
			if(numTurns + 1 == game.getMarks().size() * players.size())
				nextTurnButton.setEnabled(false);
			prevTurnButton.setEnabled(true);
			
			Log.v(TAG, "Mod: " + numTurns % players.size() + ", Turn: " + numTurns + ", Size: " + (game.getMarks().size() * players.size()));
			
			if(numTurns % players.size() == 0 ){
				game.setCurrentRound(game.getCurrentRound() + 1);
				
				Log.v(TAG, "Round: " + game.getCurrentRound());
				
				if(!(game.getCurrentRound() >= game.getMarks().size() * players.size())){
					round_mark.setText(game.getMarks().get(game.getCurrentRound()).toString());
				}


				checkMultiplers();
			}else{
				
			}
			updatePlayerName();
		}
	};
	


	View.OnClickListener prevTurnHandler = new View.OnClickListener() {

		//Goes to the previous round or player
		@Override
		public void onClick(View v) {
			numTurns--;
			if(numTurns == 0)
				prevTurnButton.setEnabled(false);
			nextTurnButton.setEnabled(true);
			
			Log.v(TAG, "Mod: " + numTurns % players.size() + ", Turn: " + numTurns);
			

			if(numTurns % players.size() == players.size() - 1){
				
				if(numTurns != 0)
					game.setCurrentRound(game.getCurrentRound() - 1);
				Log.v(TAG, "Round: " + game.getCurrentRound());	
				checkMultiplers();		
			}
			round_mark.setText(game.getMarks().get(game.getCurrentRound()).toString());
			
			updatePlayerName();
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hammer_game, menu);
		return true;
	}

	public void updatePlayerName(){
		TextView playerName = (TextView) findViewById(R.id.textView1);
		playerName.setText(playerNames.get(numTurns % players.size()));
	}

	public void checkMultiplers(){
		if(game.getCurrentRound() == 7){
			dart2_text.setText("x3");
			dart3_text.setText("x5");
		}else{
			dart2_text.setText("x2");
			dart3_text.setText("x3");
		}
	}

	public void startGame(){
		ArrayList<Integer> marks = game.getMarks();
		for(int i = 0; i < marks.size(); i++){
			for(int j = 0; j < players.size(); j++){
				players.get(j);
			}
		}
	}
}
