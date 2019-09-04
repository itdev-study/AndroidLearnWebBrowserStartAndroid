package net.telworks.androidlearnwebbrowserstartandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText strUrl;
    WebView webvMainWeb;
    ImageButton btnBack, btnForward, btnRefresh, btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webvMainWeb = (WebView)findViewById(R.id.webvMainWeb);
        strUrl = (EditText)findViewById(R.id.strURL);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        btnBack = (ImageButton)findViewById(R.id.btnBack);
        btnForward = (ImageButton)findViewById(R.id.btnForward);
        btnRefresh = (ImageButton)findViewById(R.id.btnRefresh);
        btnRequest = (ImageButton)findViewById(R.id.btnRequest);

        webvMainWeb.setWebViewClient(new WebViewClient());
        WebSettings webset = webvMainWeb.getSettings();
        webset.setJavaScriptEnabled(true);

        webvMainWeb.loadUrl("https://www.google.com");




    }
}
