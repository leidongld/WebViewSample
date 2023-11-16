package com.lllddd.webviewsample.clients;

import android.content.Context;
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
public class CSSClient extends WebViewClient {
    private Context mContext;

    public CSSClient(Context context) {
        this.mContext = context;
    }

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        String url = request.getUrl().toString();
        if (url.endsWith(".css")) {
            // 注入本地 JS 文件
            InputStream stream = null;
            try {
                stream = mContext.getAssets().open("local.css");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new WebResourceResponse("application/javascript", "UTF-8", stream);
        }
        // 使用默认方式加载其他资源
        return super.shouldInterceptRequest(view, request);
    }
}
