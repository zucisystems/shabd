package com.example.navi;

import android.R.integer;

public class WordToNumeric {
	
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    // only got here if we didn't return false
	    return true;
	}
	public static int convertToNumeric(String input){
		int i = 0 ;
		if(isInteger(input))
		{
			i=Integer.parseInt(input); 
			return i;
		}
		else{
			
		switch(input.toUpperCase())
		{
		case "ZERO":break;	
		case "ONE":i=1;
					break;	
		case "TWO":i=2;
		break;
		case "THREE":i=3;
		break;
		case "FOUR":i=4;
		break;
		case "FIVE":i=5;
		break;
		case "SIX":i=6;
		break;
		case "SEVEN":i=7;
		break;
		case "EIGHT":i=8;
		break;
		case "NINE":i=9;
		break;
		default: i=99;
		     break;
		
		}
		}
		return i;
		
				
	
	}

}
