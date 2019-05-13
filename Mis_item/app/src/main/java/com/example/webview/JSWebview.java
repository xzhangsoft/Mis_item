package com.example.webview;

import android.util.Log;
import android.webkit.JavascriptInterface;

public class JSWebview {
    private static final String TAG = "JsForWebview";

    private IJsBridge iJsBridge;

    public JSWebview(IJsBridge iJsBridge) {

        this.iJsBridge = iJsBridge;
    }

    /*
     * 这个方法不在主线程，因此不可在这set TextView
     * */
    @JavascriptInterface
    public void setValues(String value) {
        Log.v(TAG, "value:" + value);
//        iJsBridge.setTextViewValue(value);
//        Intent intent = new Intent(JSWebview.this,LoginActivity.class);

    }
}
