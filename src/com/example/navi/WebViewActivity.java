package com.example.navi;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

	private WebView webView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.web);
		//setContentView(webView);
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		// webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.loadUrl("http://www.google.com");
		//setContentView(webView);
		webView.setWebViewClient(new WebViewController());
	}

	public class WebViewController extends WebViewClient {

	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String webView) {
	        view.loadUrl(webView);
	        return true;
	    }
	}
}

	

