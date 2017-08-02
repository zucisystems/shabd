package com.example.navi;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.annotation.SuppressLint;

import android.support.v7.app.ActionBarActivity;

@SuppressWarnings("deprecation")
@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })

public class WebViewServiceNonFunctionalTesting extends ActionBarActivity {
	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);

		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new Callback());  //HERE IS THE MAIN CHANGE
		webView.loadUrl("http://www.zucisystems.com/non-functional-testing.html");
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.webmenu, menu);
        return true;
    }
 
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.back:
        this.finish();
        return true;
        default:
        return super.onOptionsItemSelected(item);
        }
    }
}
