package com.example.navi;

import java.io.File;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.lowagie.text.Document;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, OnInitListener {

		Document doc = new Document();
	
	private CounterThread mCounterThread;
	String st=new String();
	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	List<String> speakList=new ArrayList();
	private TextToSpeech tts;
	// take an integer variable for SPEECH and intiate as 1
	protected static final int RESULT_SPEECH = 1;

	// take two Image Buttons
	private ImageButton btnSpeak;
	private ImageView btnRecord;
	private TextView txtText1;
	private TextView txtproblem;
	public int i=1;
	
	
	// take one textview
	private TextView txtText;

	// Take a class TextToSpeech for using of Text To Speech
	TextToSpeech t1[]=new TextToSpeech[5];
	private CharSequence mTitle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// Intialize the TextView and  ImageButton’s
				
				txtText = (TextView) findViewById(R.id.textView);
				btnSpeak = (ImageButton) findViewById(R.id.button1);
				btnRecord = (ImageView) findViewById(R.id.imageView1);
				txtproblem = (TextView) findViewById(R.id.textViewproblem);	
				txtproblem.setText(Html.fromHtml("<a href=\"mailto:solutions@zucisystems.com\">Have a problem ?</a>"));
		        txtproblem.setMovementMethod(LinkMovementMethod.getInstance());
		        
		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();
		mCounterThread = new CounterThread();
		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
		
     /*   btnText = (Button) findViewById(R.id.btn2);
        btnText.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        	ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        	  File directory = contextWrapper.getDir(filepath, Context.MODE_PRIVATE);
        	  myInternalFile = new File(directory , filename);

        	  Button saveToInternalStorage = 
        	   (Button) findViewById(R.id.btn2);
        	  saveToInternalStorage.setOnClickListener(this);
        	  
        	  try {
        		    FileOutputStream fos = new FileOutputStream(myInternalFile);
        		    fos.write(words.getText().toString().getBytes());
        		    fos.close();
        		   } catch (IOException e) {
        		    e.printStackTrace();
        		   }
        	  
        }
         });*/
       		 tts = new TextToSpeech(this, this);
	//txtText.setText("");
	
	/*	speakOut();*/
		 t1[0] = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
			@Override
			public void onInit(int status) 
			{
				if (status != TextToSpeech.ERROR)
				{
					t1[0].setLanguage(Locale.UK);
				}
			}
		});
		 
		// set the listener setOnClickListener for button “btnSpeak”
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
		
//		new AsyncTaskRunner().execute("app");
	}
	/**
	 * Write the code for the “onActivityResult” method so that when you speak
	 * something it will be converted into text automatically and vice versa.
	 */

	/*public void createPDF() {
        Document doc = new Document();


        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ADUREC";

            File dir = new File(path);
            if (!dir.exists())
                dir.mkdirs();

            Log.d("PDFCreator", "PDF Path: " + path);

            //This is for random name
          //  ArrayList<Integer> text = new ArrayList<Integer>();
         //   for (int i = 1; i <= 10; ++i) text.add(i);
           // Collections.shuffle(text);

          File file = new File(dir, "Document" + ".pdf");
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();
        	for(int number=1;number<speakList.size();number++){
        	 
        		if((number%2!=0)){
        		
	            Paragraph p1 = new Paragraph("user  : " + speakList.get(number));
	            Font paraFont = new Font(Font.COURIER, 14.0f ,Color.RED);
	             p1.setAlignment(Paragraph.ALIGN_JUSTIFIED);
	             p1.setFont(paraFont);
	        		
	             //add paragraph to document
	             doc.add(p1);
	        		}
        		else{
	             Paragraph p2 = new Paragraph("shabd : " + speakList.get(number));
	             Font paraFont2 = new Font(Font.COURIER, 14.0f, Color.GREEN);
	             p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
	             p2.setFont(paraFont2);
	
	             doc.add(p2);
        		}
        		
        	}
           for (int number=1;number<speakList.size();number++)
            {
            	if(number==1)
            		
            	Paragraph p1=new Paragraph ("User :"+speakList.get(number));
            	Paragraph p2=new Paragraph ("Shabdh :"+speakList.get(number));
            	doc.add(p1);
            	
            }
                      
             Intent viewPdf=new Intent(Intent.ACTION_VIEW);
         	viewPdf.setDataAndType(Uri.fromFile(file), "application/pdf");
         	viewPdf.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
         	startActivity(viewPdf);
         

        } catch (DocumentException de) {
            Log.e("PDFCreator", "DocumentException:" + de);
        } catch (IOException e) {
            Log.e("PDFCreator", "ioException:" + e);
        } finally {
        	
            doc.close();
        }

    }*/

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
				 ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				 txtText.setText(text.get(0));
				 speakList.add(text.get(0));
			
				 speakOut();
				btnRecord.setOnClickListener(new View.OnClickListener() {
				//	btnRecord.setClickable(false);
					@Override
					public void onClick(View v) {
						btnRecord.setClickable(false);
						//String toSpeak = text.get(0).toString();
						//Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();

						// use the TextToSpeech class for converting the Text to
						// Speech
						//t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
						//btnRecord.setClickable(false);
					}
				});
		
		}

		
	

	@Override
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {
			
			
			if (tts.setLanguage(Locale.US)== TextToSpeech.LANG_MISSING_DATA || tts.setLanguage(Locale.US) == TextToSpeech.LANG_NOT_SUPPORTED)
			{ 
				Log.e("TTS", "This Language is not supported");
			}
			else 
			{
				btnSpeak.setEnabled(true);
			}
			
	}
		
		 else
		 {
			Log.e("TTS", "Initilization Failed!");
		}	
	}
	
	
	
	public void speakOut() {
		// TODO Auto-generated method stub
		//String text1 = txtText.getText().toString();
		
		tts.speak("Invoking Test Case Generator", TextToSpeech.QUEUE_FLUSH, null);	
		
			Toast t = Toast.makeText(getApplicationContext(),
					"Invoking Test Case Generator", Toast.LENGTH_SHORT);
			t.show();
			try {
				Thread.sleep(1000);
				startActivity(new Intent(getApplicationContext(), MindMap.class));
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}
			
				


	
	public void fb(View v)
	{
		Intent BrowserIntent3=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.facebook.com/zucisystems"));
		startActivity(BrowserIntent3);
		
	}
	public void tw(View v)
	{
		Intent BrowserIntent4=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.twitter.com/zucisystems"));
		startActivity(BrowserIntent4);
		
	}
	public void google(View v)
	{
		Intent BrowserIntent5=new Intent(Intent.ACTION_VIEW,Uri.parse("https://plus.google.com/114961389890120157565"));
		startActivity(BrowserIntent5);
		
	}
	public void in(View v)
	{
		Intent BrowserIntent6=new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/company/zuci-systems"));
		startActivity(BrowserIntent6);
		
	}
	public void gh(View v)
	{
		Intent BrowserIntent6=new Intent(Intent.ACTION_VIEW,Uri.parse("https://github.com/zucisystems"));
		startActivity(BrowserIntent6);
		
	}

	@Override
	public void onDestroy()
	{
		// Don't forget to shutdown tts!
		
		if (tts != null) {
			tts.stop();
			speakList.clear();
			tts.shutdown();
			
			
		}
		
		super.onDestroy();

        clearApplicationData();
	}
	

public void clearApplicationData() {
        File cache = getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
        String[] children = appDir.list();
        for (String s : children) {
        if (!s.equals("lib")) {
        deleteDir(new File(appDir, s));
        Log.i("EEEEEERRRRRRROOOOOOORRRR", "**************** File /data/data/APP_PACKAGE/" + s + " DELETED *******************");
        }
        }
        }
        }

        public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
        return false;
        }
        }
        }

        return dir.delete();
        }


	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
				.commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
		
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		case 5:
			mTitle = getString(R.string.title_section5);
			break;
		case 6:
			mTitle = getString(R.string.title_section6);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
	
	 /*@Override
	    public synchronized void onPause() {
	        super.onPause();
	        mCounterThread.onPause();
	    }

	    @Override
	    public synchronized void onResume() {
	        super.onResume();
	        mCounterThread.onResume();
	    }

	    public void startCounterThread() {
	        mCounterThread.start();
	    }

	    public void button_handler(View v) {
	        startCounterThread();
	    }
	    public void updateSeconds(final String seconds) {
	        Runnable UIdoWork = new Runnable() {
	            public void run() {
	                
	                txtText.setText(seconds);
	            }
	        };
	        runOnUiThread(UIdoWork);
	    }
	*/
	private class CounterThread extends Thread {
       /* private int count = 10;
        private final Object lock = new Object();
        private volatile boolean isRunning = true;

        public void onResume() {
            if(!isRunning){
                isRunning = true;
                synchronized (lock){
                    lock.notify();
                }
            }

        }

        public void onPause() {
            isRunning = false;
        }
*/
        @Override
        public void run() {
        	synchronized(st){
				st.notify();
				Toast.makeText(getApplicationContext(), "waked", Toast.LENGTH_LONG);
				}
    /*    	synchronized (lock) {
       	 tts[0].speak("Tell the name of the app", TextToSpeech.QUEUE_FLUSH, null);
			 updateSeconds("Tell the name of the app"); 
	
	 
			 if (!isRunning) try {
                 lock.wait();
             }catch (InterruptedException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
//			 	Toast.makeText(getApplicationContext(), "waked", Toast.LENGTH_LONG);
			tts[1].speak(st, TextToSpeech.QUEUE_FLUSH, null);
			updateSeconds(st); 
			
		 
			if (!isRunning) try {
                lock.wait();
            }catch (InterruptedException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
	
				if(st.equalsIgnoreCase("mind map"))
				{
					tts[3].speak(st, TextToSpeech.QUEUE_FLUSH, null);
					speakList.add(st);
					updateSeconds(st); 
			//		txtText.setText(text2);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				    
				}
                

                   
                
        	}*/

               
                
            

        }
    }
}