package net.telworks.androidlearnwebbrowserstartandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebBackForwardList;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText strUrl;
    WebView webvMainWeb;
    ImageButton btnBack, btnForward, btnRefresh, btnRequest;
    InputMethodManager inputMethodManager;
    String historyUrl;
    WebBackForwardList mWebBackForwardList;

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
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        webvMainWeb.setWebViewClient(new WebViewClient());
        WebSettings webset = webvMainWeb.getSettings();
        webset.setJavaScriptEnabled(true);
        webset.setAllowContentAccess(true);
        webset.setAppCacheEnabled(true);
        webset.setDomStorageEnabled(true);
        webset.setUseWideViewPort(true);

        webvMainWeb.loadUrl("https://www.google.com");

        strUrl.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                    String url = strUrl.getText().toString();
                    if (!url.startsWith("http://")) {
                        url = "http://" + url;
                    }

                    Toast.makeText(MainActivity.this, url, Toast.LENGTH_SHORT).show();

                    webvMainWeb.loadUrl(url);
                    inputMethodManager.hideSoftInputFromWindow(webvMainWeb.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String url = strUrl.getText().toString();
                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }

                Toast toast = Toast.makeText(MainActivity.this, url, Toast.LENGTH_LONG);
                toast.show();

                webvMainWeb.loadUrl(url);

                //InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(webvMainWeb.getWindowToken(), 0);

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mWebBackForwardList = webvMainWeb.copyBackForwardList();
                if (mWebBackForwardList.getCurrentIndex() > 0)
                 historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();

                Toast toast = Toast.makeText(MainActivity.this, "Возврат: "+historyUrl, Toast.LENGTH_LONG);
                toast.show();

                if (webvMainWeb.canGoBack()) webvMainWeb.goBack();
            }
        });

        btnForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mWebBackForwardList.getCurrentIndex() > 0)
                    historyUrl = mWebBackForwardList.getItemAtIndex(mWebBackForwardList.getCurrentIndex()-1).getUrl();

                Toast toast = Toast.makeText(MainActivity.this, "Вперед: " + historyUrl, Toast.LENGTH_LONG);
                toast.show();
                if (webvMainWeb.canGoForward()) webvMainWeb.goForward();
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(MainActivity.this, "Обновить", Toast.LENGTH_LONG);
                toast.show();
                webvMainWeb.reload();
            }
        });




    }
}
