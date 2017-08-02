package com.example.navi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Scanner;
import java.util.Date;
import java.text.*;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.Toast;

public class MindMap extends Activity implements OnClickListener,OnInitListener
{
    //String h = DateFormat.format("MM-dd-yyyyy-h-mmssaa",System.currentTimeMillis()).toString();
    //TTS object
private TextToSpeech myTTS[]=new TextToSpeech[1000];
    private TextView mText;
    private EditText mUserInput;
    private CounterThread mCounterThread;
    SharedPreferences sharedPref,sh1;
String s1=new String();
static MainActivity INSTANCE;
String data="FirstActivity";
private ImageButton btnSpeak;
private static int ttcCount=0; 
private File filenamemm =new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Shabd");
private File filenametxt =new File(Environment.getExternalStorageDirectory().getAbsolutePath(),"Shabdtest");


     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mind_map);
        mText = (TextView) findViewById(R.id.text);
        mUserInput = (EditText) findViewById(R.id.userInput);
        mCounterThread = new CounterThread();
      
        btnSpeak = (ImageButton) findViewById(R.id.button1);
        btnSpeak.setEnabled(false);
       
        //listen for clicks
   
   
    myTTS[ttcCount] = new TextToSpeech(this, this);
	
    //check for TTS data
  
         sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
         sh1=getSharedPreferences("mydata",Context.MODE_PRIVATE);
      //  File filenamemm = new File(getDir("Shabd", Context.MODE_PRIVATE),"Shabd"); //Creating an internal dir;
	      	if (!filenamemm.exists())
	      	{
	      		filenamemm.mkdirs();
	      	}  
	      //	File filenamem = getDir("Shabdtest", Context.MODE_PRIVATE); //Creating an internal dir;
	      	if (!filenametxt.exists())
	      	{
	      		filenametxt.mkdirs();
	      	} 
    //sharedPref =this.getPreferences(Context.MODE_PRIVATE);
	mCounterThread.start();
    }
    public void onClick(View v) {

        //get the text entered
        TextView enteredText = (TextView)findViewById(R.id.text);
        String words = enteredText.getText().toString();
       
        speakWords(words);
  }
 
    //speak the user text
private void speakWords(String speech) {

        //speak straight away
	
        myTTS[ttcCount].speak(speech, TextToSpeech.QUEUE_FLUSH, null);
	   ttcCount++;
	   myTTS[ttcCount] = new TextToSpeech(this, this);
}
 
    //act on result of TTS data check


    //setup TTS
public void onInit(int initStatus) {
 
        //check for successful instantiation
    if (initStatus == TextToSpeech.SUCCESS) {
    	
    	if (myTTS[ttcCount].setLanguage(Locale.US)== TextToSpeech.LANG_MISSING_DATA || myTTS[ttcCount].setLanguage(Locale.US) == TextToSpeech.LANG_NOT_SUPPORTED){
    		Log.e("TTS", "This Language is not supported");
		}
    }
    else if (initStatus == TextToSpeech.ERROR) {
        Toast.makeText(this, "Sorry! Text To Speech failed...", Toast.LENGTH_LONG).show();
    }
}

    @Override
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
            	//mUserInput.setText("I am back");
//                String time = String.valueOf(seconds);
                mText.setText(seconds);
                s1=mText.getText().toString();
            }
        };
        runOnUiThread(UIdoWork);
    }
  
   
    public static MainActivity getActivityInstance()
    {
      return INSTANCE;
    }

  public String getData()
    {
      return this.data;
    }
  private class CounterThread extends Thread {
  	
	    private final String TAG = null;
		//    private int count = 40;
	        private final Object lock = new Object();
	        private volatile boolean isRunning = true;
	        
	        public void onResume() {
	            if(!isRunning){
	                isRunning = true;
	                String userName = sharedPref.getString("userName", "Not Available");
	                
	                mUserInput.setText(userName);
	                s1=mUserInput.getText().toString();
	                synchronized (lock)
	                {
	                    lock.notify();
	                }
	            }

	        }

	        public void onPause() {
	            isRunning = false;
	        }

	        @Override
	        public void run() 
	        {
	        	try{
	        		updateSeconds("Enter the name of the App");
	        		Thread.currentThread().sleep(1000);
			      	  Scanner s=new Scanner(System.in);
			      	  
			      	SharedPreferences.Editor editor = sharedPref.edit();
			      	SharedPreferences.Editor ed1 = sh1.edit();
			      	
					 
			      	File gpxfile = new File(filenamemm,new Date().getTime() +".mm");
			    	FileWriter mw1=new FileWriter(gpxfile,true);
			    	BufferedWriter mw= new BufferedWriter(mw1);
			    	File txtfile = new File(filenametxt,new Date().getTime() +".txt");
			    	FileWriter fw1=new FileWriter(txtfile,true);
			    	BufferedWriter fw= new BufferedWriter(fw1);
			    	int no_of_modules =0;    
			      	speakWords("Enter the name of the App");
			    	
			      	editor = sharedPref.edit();
			      	ed1 = sh1.edit();
			      	ed1.putString("voice", "Enter the name of the App");
			     
			        //put your value
			        editor.putString("userName", s1);
			      	
			        ed1.commit();
			        //commits your edits
			        editor.commit();
			        synchronized (lock) 
			      	{
			      		isRunning = false;
			        	startActivity(new Intent(getApplicationContext(), Activity2.class));
			          if (!isRunning) 
			            	try 
			          		{
			                lock.wait();
	                
				            }catch (InterruptedException e) {
				                //
				            }
			      	}
			
			      	 // String App=s.next();
			      	String App=s1;
			      	  mw.write("<map version=\"1.0.1\"><!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->"
			      +"<node CREATED=\"9829\" ID=\"ID_365428423\" MODIFIED=\"9829\" TEXT=\"New Mindmap\"><node CREATED=\"5352\" MODIFIED=\"5352\" POSITION=\"right\" TEXT=\""+App+"\">");
			      	 fw.write("Story :Workflow of "+App);
			      	 fw.write("\r\nAs a tester\r\nIn order to keep track of functionality\r\nI want to generate testcases.\r\n");
			        
			      	 Thread.currentThread().sleep(500);
			      	speakWords("Enter the number of Modules");
			      	updateSeconds("Enter the number of Modules");
			      	 editor = sharedPref.edit();
			         //put your value
			      	 ed1=sh1.edit();
			      	 ed1.putString("voice","Enter the number of Modules");
			         editor.putString("userName", s1);
			
			         //commits your edits
			         editor.commit();
			         ed1.commit();
			        
			
			      	  
				      	synchronized (lock)
				      	{
				      		isRunning = false;
				        	startActivity(new Intent(getApplicationContext(), Activity2.class));
				            if (!isRunning) try {
				                lock.wait();
				                
				            }catch (InterruptedException e) {
				                //
				            }
				      	}
//	      	  int no_of_modules=s.nextInt();
				      	     
	      	 no_of_modules=WordToNumeric.convertToNumeric(s1);
			        
			        String module[]=new String[no_of_modules];
	      	  int no_of_functions;
	      	  
	      	   
	      	   String function[]=new String[50];
	      	   int no_of_pages;
	      	  
	      	   
	      	   String page[]=new String[50];
	      	   int no_of_fields;
	      	  
	      	   String field[]=new String[50];
	      	 
	      	   int no_of_sub;
	      	  
	      	   String sub[]=new String[57];
	      	     
	      	// add child to root node
	      	   fw.write("Test-Cases for functionality of the "+App+" mentioned website\r\n");
	      	  for(int i=0;i<no_of_modules;i++)
	      		   {

	      		Thread.currentThread().sleep(500);
	          	speakWords("Enter the name of the Module "+(i+1));
	          	updateSeconds("Enter the name of the Module "+(i+1));
	          	 
	          	
	          	editor = sharedPref.edit();
	          	 ed1 = sh1.edit();
	             //put your value
	          	 ed1.putString("voice","Enter the name of the Module "+(i+1));
	             editor.putString("userName", s1);
	editor.commit();
	ed1.commit();
	             
	             synchronized (lock) {
	      			isRunning = false;
	             	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                 if (!isRunning) try {
	                     lock.wait();
	                     
	                 }catch (InterruptedException e) {
	                     //
	                 }
	           	}
	      		 //  module[i]=s.next();
	      		module[i]=s1;
	      		   // add child to the child node created above
	      		Thread.currentThread().sleep(500);
	          	speakWords("Enter the number of functionalities of "+ module [i]);
	          	updateSeconds("Enter the number of functionalities of "+ module [i]);
	          	 editor = sharedPref.edit();
	             ed1 = sh1.edit();
	             //put your value
	             editor.putString("userName", s1);
	             ed1.putString("voice","Enter the number of functionalities of "+ module [i]);
	             
	             //commits your edits
	             editor.commit();
	             ed1.commit();  
	      	       System.out.println("Enter the number of functionalities of "+ module [i]+" :");
	      	     synchronized (lock) {
	      	    	isRunning = false;
	              	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                  if (!isRunning) try {
	                      lock.wait();
	                      
	                  }catch (InterruptedException e) {
	                      //
	                  }
	            	}
	  
	      		  // no_of_functions=s.nextInt();
	      	   no_of_functions=WordToNumeric.convertToNumeric(s1);
	      		   if(no_of_functions==0)
	      		  {
	      		  if((i+1)==no_of_modules)
	      		  {
	      		  mw.write("<node CREATED=\"5865\" MODIFIED=\"5865\" TEXT=\""
	      		      		+ module[i]+"\"/> </node>");
	      		  }
	      		  else
	      		  {
	      			  mw.write("<node CREATED=\"5865\" MODIFIED=\"5865 \" TEXT=\""
	      			      		+ module[i]+"\"/> ");
	      		      
	      		  }
	      		  }
	      		  else
	      		  {
	      			  mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      			      		+ module[i]+"\"> ");
	      		     
	      		  }
	      		  			   
	      		  for(int j=0;j<no_of_functions;j++)
	      		  {
	      			Thread.currentThread().sleep(500);
	              	speakWords("Enter the name of the Functionality");
	              	updateSeconds("Enter the name of the Functionality");
	              	editor = sharedPref.edit();
	              	ed1 = sh1.edit();
	              	 
	                 //put your value
	                editor.putString("userName", s1);
	                ed1.putString("voice", "Enter the name of the Functionality");  
	                 //commits your edits
	                editor.commit();
	                ed1.commit();
	      			 	synchronized (lock) {
	      				isRunning = false;
	                  	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                      if (!isRunning) try {
	                          lock.wait();
	                          
	                      }catch (InterruptedException e) {
	                          //
	                      }
	                	}
	      		 // 	  function[j]=s.next();
	      			function[j]=s1;
	      	  // add child to the child node created above
	      			Thread.currentThread().sleep(500);
	              	speakWords("Enter the number of Pages of "+ function [j]);
	              	updateSeconds("Enter the number of Pages of "+ function [j]);
	              	editor = sharedPref.edit();
	              	ed1 = sh1.edit();
	                 //put your value
	                editor.putString("userName", s1);
	                ed1.putString("voice","Enter the number of Pages of "+ function [j]); 
	                 //commits your edits
	                editor.commit();
	                ed1.commit();

	      		    
	      		  synchronized (lock) {
	      			isRunning = false;
	                	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                    if (!isRunning) try {
	                        lock.wait();
	                        
	                    }catch (InterruptedException e) {
	                        //
	                    }
	              	}
	      			 // no_of_pages=s.nextInt();
	      		no_of_pages=WordToNumeric.convertToNumeric(s1);
	      	//Mindblock for function[j] node
	      			  if(no_of_pages==0)
	      			  {
	      				Thread.currentThread().sleep(500);
	                  	speakWords("Do you want to generate testcase for the above functionality say 'Yes' or 'No')");
	                  	updateSeconds("Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	                  	editor = sharedPref.edit();
	                  	ed1 = sh1.edit();
	                     //put your value
	                  	editor.putString("userName", s1);
	                  	ed1.putString("voice", "Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	                    
	                     //commits your edits
	                    editor.commit();
	                    ed1.commit();

	      				System.out.println("Do you want to generate testcase for the above functionality?(yes/no) :");
	      				synchronized (lock) {
	      					isRunning = false;
	                    	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                        if (!isRunning) try {
	                            lock.wait();
	                            
	                            
	                        }catch (InterruptedException e) {
	                            //
	                        }

	      				}
	      				 //String choice = s.next();
	      				  String choice = s1;
	      				  {
	      				   if(choice.equalsIgnoreCase("yes"))
	      				  {
	      					 fw.write(" \r\nScenario :Generating Testcase for "+function[j]+""
	      				  		+ "\r\nGiven that I get into "+App+" and enter into "+module[i]
	      				  		+ "\r\nAnd I have "+(no_of_functions)+"in them."
	      				  		+ "\r\nWhen I enter into "+function[j]
	      				  		+ "\r\nThen It should perform the following function\r\n");
	      					}
	      				   else
	      				   {
	      					   speakWords("Proceeding to next functionality");
	                      	updateSeconds("Proceeding to next functionality");
	      					   
	      				   }
	      				  }
	      				  if((j+1)==no_of_functions)
	      				  {
	      					   if((i+1)==no_of_modules)
	      					   {
	      						   
	      					   mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      				      		+ function[j]+"\"/></node> </node>");
	      				      }
	      					   
	      					  else
	      					  {
	      						  mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      						      		+ function[j]+"\"/></node>");
	      						}
	      					  
	      			  
	      				  }
	      				  else
	      				  {
	      					  mw.write("<node CREATED=\"5865\" MODIFIED=\"5865\" TEXT=\""
	      					      		+ function[j]+"\"/> ");
	      				   }
	      			  }
	      			  else
	      			  {
	      				  mw.write("<node CREATED=\"5865\" MODIFIED=\"5865\" TEXT=\""
	      				      		+ function[j]+"\"> ");
	      			     
	      			  }
	      				 
	      			  			 
	      			  for(int k=0;k<no_of_pages;k++)
	      			  {
	      				Thread.currentThread().sleep(500);
	                  	speakWords("Enter the name of the Pages");
	                  	updateSeconds("Enter the name of the Pages");
	                  	 editor = sharedPref.edit();
	                  	 ed1 = sh1.edit();
	                     //put your value
	                     editor.putString("userName", s1);
	                    ed1.putString("voice","Enter the name of the Pages");
	                     //commits your edits
	                     editor.commit();
	                     ed1.commit();
      				  
	      				synchronized (lock) {
	      					isRunning = false;
	                    	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                        if (!isRunning) try {
	                            lock.wait();
	                            
	                        }catch (InterruptedException e) {
	                            //
	                        }
	                  	}
	      			  //page[k]=s.next();
	      				page[k]=s1;
	      			        			
	      		  // add child to the child node created above
	      				Thread.currentThread().sleep(500);
	                  	speakWords("Enter the number of Fields of "+ page [k]);
	                  	updateSeconds("Enter the number of Fields of "+ page [k]);
	                  	 editor = sharedPref.edit();
	                     //put your value
	                  	 ed1 = sh1.edit();
	                     editor.putString("userName", s1);
	                     ed1.putString("voice", "Enter the number of Fields of "+ page [k]); 
	                     //commits your edits
	                     editor.commit();
	                     ed1.commit();
	      			    
	      			  synchronized (lock) {
	      				isRunning = false;
	                  	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                      if (!isRunning) try {
	                          lock.wait();
	                          
	                      }catch (InterruptedException e) {
	                          //
	                      }
	                	}
	      				  //no_of_fields=s.nextInt();
	      			no_of_fields=WordToNumeric.convertToNumeric(s1);
	      				  //Mindblock of pages[k]
	      			if(no_of_fields==0)
	      			{
	      				Thread.currentThread().sleep(500);
	                  	speakWords("Do you want to generate testcase for the above functionality say 'Yes' or 'No')");
	                  	updateSeconds("Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	                  	 editor = sharedPref.edit();
	                     //put your value
	                     editor.putString("userName", s1);
	                     ed1.putString("voice", "Do you want to generate testcase for the above functionality say 'Yes' or 'No'");

	                     //commits your edits
	                     editor.commit();
	                     ed1.commit();

	      				synchronized (lock) {
	      					isRunning = false;
	                      	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                          if (!isRunning) try {
	                              lock.wait();
	                              
	                          }catch (InterruptedException e) {
	                              //
	                          }
	                    	}
	      				 // String choice = s.next();
	      				 String choice = s1;
	      				  {
	      				   if(choice.equalsIgnoreCase("yes"))
	      				  {
	      				  fw.write("\r\nScenario : Generating Testcase for the function "+page[k]
	      						   +"\r\nGiven that I get into "+App+" and enter into "+module[i]
	      					  		+ "\r\nAnd I have "+(no_of_functions)+"in them."
	      					  		+ "\r\nWhen I enter into "+function[j]+"it leads to "+page[k]
	      					  		+ "\r\nThen the "+page[k]+" perform the following function\r\n");
	      				  }
	      				   else
	      				   {
	      					 //Thread.currentThread().sleep(3000);
	                       	speakWords("Proceeding to next functionality");
	                       	updateSeconds("Proceeding to next functionality");
	      					   System.out.println("Proceeding to next functionality");
	      				   }
	      				  }
	      				if((k+1)==no_of_pages)
	      				{
	      					if((j+1)==no_of_functions)
	      					{
	      						if((i+1)==no_of_modules)
	      						{
	      							
	      							mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      						      		+ page[k]+"\"/> ");
	      					  
	      							mw.write("</node></node></node>");
	      					
	      							
	      						}
	      						
	      					else{
	      						mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      					      		+ page[k]+"\"/> ");
	      				  
	      						mw.write("</node></node>");
	      					}
	      					}
	      					else
	      					{
	      						mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      					      		+ page[k]+"\"/> ");
	      						mw.write("</node>");
	      				  
	      					}
	      				}
	      				else
	      				{
	      					mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      				      		+ page[k]+"\"/> ");
	      			  
	      				}
	      			                       
	      			}
	      			
	      			else
	      			{
	      				mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      			      		+ page[k]+"\"> ");
	      		  
	      			}			 				  
	      				  
	      				  for(int l=0;l<no_of_fields;l++)
	      				  {
	      					Thread.currentThread().sleep(500);
	                      	speakWords("Enter the name of the Fields");
	                      	updateSeconds("Enter the name of the Fields");
	                      	 editor = sharedPref.edit();
	                         ed1 = sh1.edit();
	                         editor.putString("userName", s1);
	                         ed1.putString("voice","Enter the name of the Fields");
	                         //commits your edits
	                         editor.commit();
	                         ed1.commit();

	      					  System.out.println("Enter the name of the Fields");
	      					synchronized (lock) {
	      						isRunning = false;
	                          	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                              if (!isRunning) try {
	                                  lock.wait();
	                                  
	                              }catch (InterruptedException e) {
	                                  //
	                              }
	                        	}
	      				 // field[l]=s.next();
	      					 field[l]=s1;
	      					 fw.write("* The "+page[k]+" gets you to "+field[l]+" \r\n");
	      					Thread.currentThread().sleep(500);
	                      	speakWords("Enter the number of Fields of "+ field[l]);
	                      	updateSeconds("Enter the number of Fields of "+ field[l]);
	                      	 editor = sharedPref.edit();
	                      	 ed1 = sh1.edit();
	                         //put your value
	                         editor.putString("userName", s1);
	                         ed1.putString("voice","Enter the number of Fields of "+ field[l]);
	                         //commits your edits
	                         editor.commit();
	                         ed1.commit();


	      				  System.out.println("Enter the number of Fields of "+ field[l]+" :");
	      				synchronized (lock) {
	      					isRunning = false;
	                      	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                          if (!isRunning) try {
	                              lock.wait();
	                              
	                          }catch (InterruptedException e) {
	                              //
	                          }
	                    	}
	      				
	      				//  no_of_sub=s.nextInt();
	      				
	      			  no_of_sub=WordToNumeric.convertToNumeric(s1);
	      			  
	      				 
	      			  // add child to the child node created above
	      				  //minddata add to field
	      		if(no_of_sub==0)
	      		{
	      			Thread.currentThread().sleep(500);
	              	speakWords("Do you want to generate testcase for the above functionality say 'Yes' or 'No')");
	              	updateSeconds("Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	              	 editor = sharedPref.edit();
	              	 ed1 = sh1.edit();
	                 //put your value
	                 editor.putString("userName", s1);
	                 editor.putString("voice","Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	                 //commits your edits
	                 editor.commit();
	                 ed1.commit();

	      			System.out.println("Do you want to generate testcase for the above functionality?(yes/no) :");
	      			synchronized (lock) {
	      				isRunning = false;
	                  	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                      if (!isRunning) try {
	                          lock.wait();
	                          
	                      }catch (InterruptedException e) {
	                          //
	                      }
	                	}
	      			  //String choice = s.next();
	      			String choice =s1;
	      			  
	      			   if(choice.equalsIgnoreCase("yes"))
	      			  {
	      					  fw.write("\r\nScenario : Generating Testcase for the function "+field[l]
	      							   +"\r\nGiven that I get into "+App+" and enter into "+module[i]
	      						  		+ "\r\nAnd I have "+(no_of_functions)+"in them."
	      						  		+ "\r\nWhen I enter into "+function[j]+"it leads to "+page[k]
	      						  		+ "\r\nThen the "+page[k]+" is redirected to "+field[l]+" and performs the following funtion\r\n");
	      			  }
	      			   else
	      			   {
	      				 //Thread.currentThread().sleep(5000);
	                   	speakWords("Proceeding to next functionality");
	                   	updateSeconds("Proceeding to next functionality");
	            	   System.out.println("Proceeding to next functionality");
	      			   }
	      			  
	      			
	      				  if((l+1)==no_of_fields)
	      				  {
	      					  if((k+1)==no_of_pages)
	      					  {
	      						 if((j+1)==no_of_functions)
	      						 {
	      							 
	      							 if((i+1)==no_of_modules)
	      							 {
	      								 mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      								      		+ field[l]+"\"/> ");
	      								 mw.write("</node></node></node></node>");
	      							 }
	      							 else
	      							 {
	      								 mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      								      		+ field[l]+"\"/> ");
	      								 mw.write("</node></node></node>");
	      							 }
	      						 }
	      						 else
	      						 {
	      							 mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      							      		+ field[l]+"\"/> ");
	      							 mw.write("</node></node>");
	      						 }
	      					  }
	      					  else
	      					  {
	      						  mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      						      		+ field[l]+"\"/> ");
	      						  mw.write("</node>");
	      					  
	      					  }
	      					  
	      				  }
	      				  else
	      				  {
	      					  mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      					      		+ field[l]+"\"/> ");
	      				   }
	      		}
	      		else
	      		{
	      			mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	      		      		+ field[l]+"\"> ");
	      	   	}
	      				  for(int m=0;m<no_of_sub;m++)
	      				
	      				  {
	      					Thread.currentThread().sleep(500);
	      	              	speakWords("Enter the name of the Subfields of "+field[l]);
	      	              	updateSeconds("Enter the name of the subfields of "+field[l]);
	      	              	editor = sharedPref.edit();
	      	                 //put your value
	      	              	 ed1 = sh1.edit();
	      	              	 editor.putString("userName", s1);
	      	              	 ed1.putString("voice", "Enter the name of the subfields of "+field[l]);
	      	                 //commits your edits
	      	                 editor.commit();
	      	                 ed1.commit();

	      					  
	      				  System.out.println("Enter the name of the subfields of "+field[l]);
	      				  synchronized (lock) {
	      				isRunning = false;
	                      startActivity(new Intent(getApplicationContext(), Activity2.class));
	                          if (!isRunning) try {
	                              lock.wait();
	                              
	                          }catch (InterruptedException e) {
	                              //
	                          }
	                    	}
	          			
	      				sub[m]=s1;
	      				  //sub[m]=s.next();
	      				Thread.currentThread().sleep(500);
	  	              	speakWords("Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	  	              	updateSeconds("Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	  	              	editor = sharedPref.edit();
	  	                 //put your value
	  	              	 ed1= sh1.edit();
	  	              	 ed1.putString("voice","Do you want to generate testcase for the above functionality say 'Yes' or 'No'");
	  	                 editor.putString("userName", s1);

	  	                 //commits your edits
	  	                 editor.commit();
	  	                 ed1.commit();

	           			 System.out.println("Do you want to generate testcase for the above functionality?(yes/no) :");
	           			synchronized (lock) {
	           				isRunning = false;
	                      	startActivity(new Intent(getApplicationContext(), Activity2.class));
	                          if (!isRunning) try {
	                              lock.wait();
	                              
	                          }catch (InterruptedException e) {
	                              //
	                          }
	                    	}
	           			String choice = s1;
	         			 // String choice = s.next();
	         			  
	         			   if(choice.equalsIgnoreCase("yes"))
	         			  {
	         				  fw.write("\r\nScenario : Generating Testcase for the function "+sub[m]
	      						   +"\r\nGiven that I get into "+App+" and enter into "+module[i]
	      					  		+ "\r\nAnd I have "+(no_of_functions)+"in them."
	      					  		+ "\r\nWhen I enter into "+function[j]+"it leads to "+page[k]
	      					  		+ "\r\nThen the "+page[k]+" is redirected to "+field[l]+" "
	      					  				+ "\r\nFinally, it leads you to "+sub[m]+"\r\n");
	      		  			}
	         			   else
	         			   {
	         				  //Thread.currentThread().sleep(5000);
	        	              	speakWords("Proceeding to next functionality");
	        	              	updateSeconds("Proceeding to next functionality");
	         				   System.out.println("Proceeding to next functionality");
	         			   }
	         			  
	         			
	      			  // add child to the child node created above
	           			if((m+1)==no_of_sub)
	           			{
	           				if((l+1)==no_of_fields)
	           				{
	           					if((k+1)==no_of_pages)
	           					{
	           						if((j+1)==no_of_functions)
	           						{
	           							if((i+1)==no_of_modules)
	           							{
	           								mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	               						      		+ sub[m]+"\"/> ");
	               							mw.write("</node></node></node></node></node>");
	           								
	           							}
	           							else
	           							{
	           								mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	               						      		+ sub[m]+"\"/> ");
	               							mw.write("</node></node></node></node>");
	           							}
	           						}
	           						else
	           						{
	           							mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	           						      		+ sub[m]+"\"/> ");
	           							mw.write("</node></node></node>");
	           						}
	           						
	           					}
	           					else
	           					{
	           						mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	           					      		+ sub[m]+"\"/> ");
	           						mw.write("</node></node>");
	           					}
	           					
	           				}
	           				else
	           				{
	           					mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	           				      		+ sub[m]+"\"/> ");
	           					mw.write("</node>");
	           				}
	           				
	           			}
	           			else
	           			{
	           				mw.write("<node CREATED=\"5818\" MODIFIED=\"5818\" TEXT=\""
	           			      		+ sub[m]+"\"/> ");
	           			}
	      		  }
	      		  
	      		  }
	      	
	      	  }
	      		  
	      		   }
	      		   }
	      	speakWords("Test Case completed");
	      	
	      	mw.write("</node></map>");
			mw.close();
			mw1.close();
			fw.close();
			fw1.close();
			
			//s.close();
			
			Thread.currentThread().sleep(500);
			ttcCount--;
	        for(;ttcCount>0;ttcCount--)
	        myTTS[ttcCount].shutdown();
	        myTTS[ttcCount].stop();
			finish();
	        	}
	        	catch(Exception e){
	        		e.printStackTrace();
	        	}
	     }
	  }
	}