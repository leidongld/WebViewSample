package com.lllddd.webviewsample.clients;

import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: lllddd
 * created on: 2023/11/3 10:36
 * description:
 */
public class HtmlClient extends WebViewClient {
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        // TODO: 2023/11/6 看下线程+耗时、缓存下载路径干预
        String url = request.getUrl().toString();
        if (url.contains("baidu")) {
            try {
                // 获取html资源并进行自定义处理
                InputStream is = view.getContext().getAssets().open("local.html");
                return new WebResourceResponse("text/html", "utf-8", is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.shouldInterceptRequest(view, request);
    }
}
