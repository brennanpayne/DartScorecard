package com.brennan.dartscorecard;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
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
	private ArrayList<Player> players;
	private ArrayList<TextView> playersText;
	private ArrayList<Button> dartOneButtons, dartTwoButtons, dartThreeButtons;
	private int numTurns, currentPlayer;
	private Button nextTurnButton, prevTurnButton;
	private final Integer SELECTED_TAG = 1;
	private final Integer UNSELECTED_TAG = 2;
	HammerGame game;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//Retrieve extras
		Bundle extras = getIntent().getExtras();
		if(extras != null){
			players = extras.getParcelableArrayList("players");
		}


		setContentView(R.layout.activity_hammer_game);

		mRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

		//Round and multiplier text
		round_mark = (TextView) findViewById(R.id.round_mark);
		dart2_text = (TextView) findViewById(R.id.dart2_text);
		dart3_text = (TextView) findViewById(R.id.dart3_text);		

		//Next Turn button
		nextTurnButton = (Button) findViewById(R.id.next_turn_button);
		nextTurnButton.setOnClickListener(nextTurnHandler);

		//Previous turn button
		prevTurnButton = (Button) findViewById(R.id.prev_turn_button);
		prevTurnButton.setEnabled(false);
		prevTurnButton.setOnClickListener(prevTurnHandler);

		//Dart buttons
		//TODO: See if there is a better way to do this more programatically
		//Possible solution: http://stackoverflow.com/questions/6663380/is-it-possible-to-write-a-for-loop-to-assign-listeners-to-many-button-with-the-s
		dartOneButtons = new ArrayList<Button>();
		Button b11 = (Button) findViewById(R.id.dart1times1);
		Button b12 = (Button) findViewById(R.id.dart1times2);
		Button b13 = (Button) findViewById(R.id.dart1times3);
		dartOneButtons.add(b11);
		dartOneButtons.add(b12);
		dartOneButtons.add(b13);

		dartTwoButtons = new ArrayList<Button>();
		Button b21 = (Button) findViewById(R.id.dart2times1);
		Button b22 = (Button) findViewById(R.id.dart2times2);
		Button b23 = (Button) findViewById(R.id.dart2times3);
		dartTwoButtons.add(b21);
		dartTwoButtons.add(b22);
		dartTwoButtons.add(b23);

		dartThreeButtons = new ArrayList<Button>();
		Button b31 = (Button) findViewById(R.id.dart3times1);
		Button b32 = (Button) findViewById(R.id.dart3times2);
		Button b33 = (Button) findViewById(R.id.dart3times3);
		dartThreeButtons.add(b31);
		dartThreeButtons.add(b32);
		dartThreeButtons.add(b33);


		for(int i = 0; i < 3; i++){
			dartOneButtons.get(i).setOnClickListener(dartHandler);
			dartTwoButtons.get(i).setOnClickListener(dartHandler);
			dartThreeButtons.get(i).setOnClickListener(dartHandler);
		}

		//Set up players names and scores default to 0
		TextView tv1 = (TextView) findViewById(R.id.playerOneText);
		TextView tv2 = (TextView) findViewById(R.id.playerTwoText);
		TextView tv3 = (TextView) findViewById(R.id.playerThreeText);
		TextView tv4 = (TextView) findViewById(R.id.playerFourText);

		playersText = new ArrayList<TextView>();
		playersText.add(tv1);
		playersText.add(tv2);
		playersText.add(tv3);
		playersText.add(tv4);

		for(int i = 0; i < players.size(); i++){
			TextView tv = playersText.get(i);
			tv.setVisibility(View.VISIBLE);
			tv.setText(players.get(i).getName()  + "\nScore: 0" ) ;
		}

		//Set up game and numTurns
		game = new HammerGame();
		game.addPlayers(players);
		numTurns = 0;
		currentPlayer = -1;

	}

	View.OnClickListener dartHandler = new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.dart1times1:
			case R.id.dart1times2: 
			case R.id.dart1times3:
				checkDartButtons(v, dartOneButtons);
				break;
			case R.id.dart2times1:
			case R.id.dart2times2:
			case R.id.dart2times3:
				checkDartButtons(v, dartTwoButtons);
				break;
			case R.id.dart3times1:
			case R.id.dart3times2:
			case R.id.dart3times3:
				checkDartButtons(v, dartThreeButtons);
				break;
			}


		}
	};

	public void checkDartButtons(View v, ArrayList<Button> buttons){
		for(int i = 0; i < buttons.size(); i++){
			if(buttons.get(i).getId() == v.getId() && buttons.get(i).getTag() != SELECTED_TAG){
				buttons.get(i).setBackgroundColor(getResources().getColor(R.color.light_green));
				buttons.get(i).setTag(SELECTED_TAG);
			}else{
				buttons.get(i).setBackgroundColor(getResources().getColor(R.color.white));	
				buttons.get(i).setTag(UNSELECTED_TAG);
			}
		}
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

			currentPlayer = numTurns % players.size();
			Log.v(TAG, "CurrentPlayer: " + currentPlayer + ", Turn: " + numTurns);

			if(currentPlayer == 0){
				game.setCurrentRound(game.getCurrentRound() + 1);				

				if(!(game.getCurrentRound() >= game.getMarks().size() * players.size())){
					round_mark.setText(game.getMarks().get(game.getCurrentRound()).toString());
				}

				Log.v(TAG, "Round: " + game.getCurrentRound());
				checkMultiplers();
			}			
			updatePlayerScore(currentPlayer);
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

			currentPlayer = numTurns % players.size();
			Log.v(TAG, "CurrentPlayer: " + currentPlayer + ", Turn: " + numTurns);

			if(currentPlayer == players.size() - 1 || players.size() == 0){				
				if(game.getCurrentRound() != 0)
					game.setCurrentRound(game.getCurrentRound() - 1);

				Log.v(TAG, "Round: " + game.getCurrentRound());	
				round_mark.setText(game.getMarks().get(game.getCurrentRound()).toString());
				checkMultiplers();		
			}			
			updatePlayerScore(currentPlayer);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hammer_game, menu);
		return true;
	}

	public void updatePlayerScore(int index){
		TextView playerName = playersText.get(index);
		playerName.setText(players.get(index).getName() + "\nScore: " + (players.get(index).getScore() ));
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

}
