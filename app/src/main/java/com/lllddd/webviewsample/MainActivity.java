package com.lllddd.webviewsample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.lllddd.webviewsample.clients.CSSClient;
import com.lllddd.webviewsample.clients.HtmlClient;
import com.lllddd.webviewsample.clients.ImageClient;
import com.lllddd.webviewsample.clients.JsClient;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {
    private WebView mWebView;

    private Button mBtnLoad;

    private Button mBtnHtml;

    private Button mBtnCSS;

    private Button mBtnJs;

    private Button mBtnPng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWidgets() {
        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDatabaseEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new WebChromeClient());

        mBtnLoad = findViewById(R.id.btn_load_baidu);
        mBtnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("https://www.baidu.com");
            }
        });

        mBtnHtml = findViewById(R.id.btn_html);
        mBtnHtml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterceptHtml();
            }
        });

        mBtnCSS = findViewById(R.id.btn_css);
        mBtnCSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterceptCSS();
            }
        });

        mBtnJs = findViewById(R.id.btn_js);
        mBtnJs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterceptJs();
            }
        });

        mBtnPng = findViewById(R.id.btn_png);
        mBtnPng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterceptPNG();
            }
        });
    }

    private void onClickInterceptPNG() {
        mWebView.setWebViewClient(new ImageClient());
        mWebView.loadUrl("https://images.pexels.com/photos/1751537/pexels-photo-1751537.jpeg?auto=compress&cs=tinysrgb&w=600");
    }

    private void onClickInterceptJs() {
        mWebView.setWebViewClient(new JsClient(this));
        mWebView.loadUrl("https://www.youku.com/");
    }

    private void onClickInterceptCSS() {
        mWebView.setWebViewClient(new CSSClient(this));
        mWebView.loadUrl("https://www.youku.com/");
    }

    CountDownLatch countDownLatch = new CountDownLatch(1);

    private void onClickInterceptHtml() {
        mWebView.setWebViewClient(new HtmlClient());
        mWebView.loadUrl("https://www.baidu.com");
    }

    @Override
    protected void onDestroy() {
        recycleWebView();
        super.onDestroy();
    }

    private void recycleWebView() {
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ConstraintLayout) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }
    }
}