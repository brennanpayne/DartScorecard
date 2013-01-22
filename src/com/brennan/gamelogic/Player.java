package com.brennan.gamelogic;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Player implements Parcelable{
	private static final String TAG = "PlayerClass";
	
	private int score;
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void addToScore(int score){
		this.score += score;
	}
	//Standard Constructor
	public Player(String name){
		this.name = name;
		this.score = 0;
	}
	
	//Constructor for parcelable objects
	public Player(Parcel in){
		readFromParcel(in);
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		Log.v(TAG, "writeToParcel..." + flags);	
		dest.writeInt(score);
		dest.writeString(name);	
	}
	
	public void readFromParcel(Parcel in){
		score = in.readInt();
		name = in.readString();
	}
	
	
    /**
    *
    * This field is needed for Android to be able to
    * create new objects, individually or as arrays.
    */
   public static final Parcelable.Creator CREATOR =
   	new Parcelable.Creator() {
           public Player createFromParcel(Parcel in) {
               return new Player(in);
           }

           public Player[] newArray(int size) {
               return new Player[size];
           }
       };
}


