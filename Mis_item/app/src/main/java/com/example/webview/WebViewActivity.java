package com.example.webview;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mis_item.R;

public class WebViewActivity extends AppCompatActivity implements IJsBridge {
    private static final String TAG = "WebViewActivity";

    WebView mWebView;
    WebSettings webSettings;

    String username, password;

    Handler mHandler;

    private long exitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mWebView = (WebView) findViewById(R.id.webView);
        webSettings = mWebView.getSettings();

        mWebView.setWebViewClient(new WebViewClient());
        getIntentData();
        initWebSetting();
        openUrlDir();
//        openWebViewNetUrl();
//        loadData();
    }

    private void loadData() {
        String body = "WebView.loadDataWithBaseURL(String baseUrl, String data, String mimeType, String encoding, String historyUrl))";
        mWebView.loadDataWithBaseURL("https://www.baidu.com/", body, "text/html", "utf-8",null);
    }

    private void openUrlDir() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("https://www.jianshu.com");

        mWebView.setWebViewClient(new WebViewClient() {
            //在webview里打开新链接
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
                Log.e("HEHE", "Cookies = " + CookieStr);
                super.onPageFinished(view, url);

            }
        });
    }


    private void getIntentData() {
        Intent intent = getIntent();
        Bundle data = intent.getBundleExtra("user");
        username = data.getString("username");
        password = data.getString("password");
        Log.v(TAG, username + password);
    }

    private void initWebSetting() {
        //允许webview加载js
        webSettings.setJavaScriptEnabled(true);
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        // 缩放至屏幕的大小
//        webSettings.setLoadWithOverviewMode(true);
        //支持缩放，默认为true。
//        webSettings.setSupportZoom(true);
        //自行设置初始的缩放比例
//        mWebView.setInitialScale(25);
        //Android自带五个可选字体大小的值：SMALLEST(50%),SMALLER(75%),NORMAL(100%),LARGER(150%), LARGEST(200%)。
        webSettings.setTextSize(WebSettings.TextSize.LARGER);
        //设置内置的缩放控件。
        webSettings.setBuiltInZoomControls(true);
        //隐藏原生的缩放控件
        webSettings.setDisplayZoomControls(false);
        //关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //支持自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //设置编码格式
        webSettings.setDefaultTextEncodingName("utf-8");
        //跨域设置(目前需要在api level 16以上使用)
        webSettings.setAllowUniversalAccessFromFileURLs(true);
    }

    private void openWebViewNetUrl() {
        mHandler = new Handler();
//        mWebView.loadUrl("https://angular.io/");

        mWebView.setWebChromeClient(new MyWebChromeClient());

        //给webview添加js接口
        mWebView.addJavascriptInterface(new JSWebview(this), "WebViewActivity");
        mWebView.loadUrl("file:///android_asset/index.html");
        mWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            Log.v(TAG,username);
                            String jsMethodName = "callJS('" + username + "')";
//                    String jsMethodName = "test()";
                            mWebView.evaluateJavascript(jsMethodName, new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String responseJson) {
                                    Log.d("JSMethod", "调用js返回值:"+ responseJson);
                                }
                            });
                        } else {
                            //SDK <= 19，暂时没找到方案
                        }
                    }
                });
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                super.onBackPressed();
            }

        }
    }


    @Override
    public void setTextViewValue(String value) {
        Log.v(TAG,value);

//        Bundle data = new Bundle();
//        data.putString("value", value);
//
//        Intent intent = new Intent(WebViewActivity.this,LoginActivity.class);
//        intent.putExtra("fromIndex", data);
//        startActivity(intent);
    }

    final class MyWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Log.d("onJsAlert", message);
            result.confirm();

//            btn.setText(message);
            return true;
        }
    }
}
