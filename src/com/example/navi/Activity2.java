package com.example.navi;



import java.util.ArrayList;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends Activity {
	private TextView mText;
    private EditText mUserInput;
	private ImageButton btnSpeak;
	private String data1;
	public static String sb;
	SharedPreferences sharedPref,sh1;
	protected static final int RESULT_SPEECH = 1;
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mind_map);
	        mText = (TextView) findViewById(R.id.text);
	        mUserInput = (EditText) findViewById(R.id.userInput);
	        //sharedPref=getSharedPreferences("userName", Context.MODE_PRIVATE);
	        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
	        sh1 = getSharedPreferences("mydata",Context.MODE_PRIVATE);
	        String userName = sh1.getString("voice", "Not Available");
            
	        mText.setText(userName);
	        btnSpeak = (ImageButton) findViewById(R.id.button1);
	        /*data=MainActivity.getActivityInstance().getData();  
	        Toast.makeText(getApplicationContext(),"Data from first activity is"+data, Toast.LENGTH_LONG).show();*/
	        btnSpeak.setOnClickListener(new View.OnClickListener() {

				@Override
			public void onClick(View v) {
						
					Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

					// Get the value using Intent from Speech
					intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

					try {
						startActivityForResult(intent, RESULT_SPEECH);
						// Set the Text as Empty
					//	txtText.setText("");
					} catch (ActivityNotFoundException a) {
						// Show a Toast if the device is not supported
						Toast t = Toast.makeText(getApplicationContext(),
								"Opps! Your device doesn't support Speech to Text", Toast.LENGTH_SHORT);
						t.show();
					}
				}
			});
        
	    }

public void clicktype(View view)
{
	  sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
      //now get Editor
      SharedPreferences.Editor editor = sharedPref.edit();
      //put your value
      editor.putString("userName",mUserInput.getText().toString());
String temp=mUserInput.getText().toString();
      //commits your edits
      editor.commit();
      createAndShowAlertDialog();
	
}
	 
	 @Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	
	// check the requestCode as a case
/*	switch (requestCode) {
	case RESULT_SPEECH: {
		if (resultCode == RESULT_OK && null != data) {
			// t1=new TextToSpeech(this, (OnInitListener) this);
			tts = new TextToSpeech(this, this);*/
	try{
			 ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			 mUserInput.setText(text.get(0));
			  sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		        //now get Editor
		        SharedPreferences.Editor editor = sharedPref.edit();
		        //put your value
		        editor.putString("userName", text.get(0));

		        //commits your edits
		        editor.commit();
		        createAndShowAlertDialog();
			 /*Intent resultIntent = new Intent();
			 data1=MainActivity.getActivityInstance().getData();
			 mUserInput.setText(data1);
		     Toast.makeText(getApplicationContext(),"Data from first activity is"+data, Toast.LENGTH_LONG).show();
			 resultIntent.putExtra(sb, text.get(0));
			 setResult(Activity.RESULT_OK, resultIntent);*/
		      /*if(data!=null)
		    	  finish();*/
	}
	catch(Exception e){
		
	}
	

	}
private void createAndShowAlertDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("MindMap");
    builder.setMessage("Is your word "+mUserInput.getText().toString()+" Correct");
    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
             //TODO
             dialog.dismiss();	
             finish();
        }
    });
    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
             //TODO
             dialog.dismiss();
        }
    });
    AlertDialog dialog = builder.create();
    dialog.show();
  }
}