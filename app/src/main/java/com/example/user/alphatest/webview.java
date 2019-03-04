package com.example.user.alphatest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webview extends AppCompatActivity {

    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        web= (WebView) findViewById(R.id.web);

        web.getSettings().setJavaScriptEnabled(true);
        String url="https://www.thebluealliance.com/events/isr/2018";
        web.loadUrl(url);
        web.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            view.loadUrl(url);
            return true;
        }
    }

    public void backtoAbilities(View view) {
        Intent b= new Intent (this, newTeam.class);
        startActivity(b);
    }
}
