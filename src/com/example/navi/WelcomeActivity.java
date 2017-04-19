package com.example.navi;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

@SuppressLint("NewApi")
public class WelcomeActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		Runnable run3 = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				nextActivity();
				finish();
			}
		};
				Handler myhandler = new Handler();
				myhandler.postDelayed(run3, 3000);
				
	}
 
	public void nextActivity()
	{
		Intent in = new Intent(this,MainActivity.class);
		startActivity(in);
	}
}
