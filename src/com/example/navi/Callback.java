package com.example.navi;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Callback extends WebViewClient{  //HERE IS THE MAIN CHANGE. 

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return (false);
    }
    abstract class  ChildList {
    	int i;
    }
}