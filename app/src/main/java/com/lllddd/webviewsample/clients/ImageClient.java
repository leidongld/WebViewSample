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
 * created on: 2023/11/3 10:35
 * description:
 */
public class ImageClient extends WebViewClient {
    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        if (url.endsWith(".png") || url.endsWith(".jpg") || url.endsWith(".jpeg") || url.contains(".jpeg")) {
            try {
                // 获取图片资源并进行自定义处理
                InputStream is = view.getContext().getAssets().open("local.jpg");
                return new WebResourceResponse("image/jpeg", "utf-8", is);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return super.shouldInterceptRequest(view, request);
    }
}
