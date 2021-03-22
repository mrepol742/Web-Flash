package com.android.DROID_MJ.webflashlight;

import android.annotation.TargetApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.camera2.CameraManager;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;

public class a extends AppCompatActivity {
    private WebView webView;
    private Parameters a;
    private ImageView b;
    private CameraManager c;
    private Camera d;
    private boolean e = false;
    private int i = 0;
    private float j = -1.0f;
    private float k = -1.0f;
    private LayoutParams l;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        webView = (WebView)findViewById(R.id.a);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
if (url.startsWith("file:///android_asset/a/b.html")) {
a();
} else if (url.startsWith("file:///android_asset/a/a.html")) {
try {
b();
} catch (Exception e) {
b();
}
}

                return false;
            }

            @TargetApi(Build.VERSION_CODES.N)
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });

        webView.loadUrl("file:///android_asset/a/a.html");


    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                        finishAffinity();
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    private void a() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (this.c == null) {
                this.c = (CameraManager) getSystemService("camera");
            } try {
                this.c.setTorchMode(this.c.getCameraIdList()[0], true);
                return;
            } catch (Exception a) {
                c(a);
                return;
            }
        } try {
            if (getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
                this.d = Camera.open();
                this.a = this.d.getParameters();
                this.a.setFlashMode("torch");
                this.d.setParameters(this.a);
                this.d.startPreview();
            }
        } catch (Exception e2) {
            c(e2);
        }
    }

    private void b() {
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                this.c.setTorchMode(this.c.getCameraIdList()[0], false);
                return;
            } catch (Exception a) {
                c(a);
                return;
            }
        } try {
            if (getPackageManager().hasSystemFeature("android.hardware.camera.flash")) {
                this.d.stopPreview();
                this.d.release();
                this.d = null;
            }
        } catch (Exception b) {
            c(b);
        }
    }

    private void c(Exception a) {
        Toast.makeText(this, "Error: " + a.getMessage(), 0).show();
    }

    
    public boolean onTouchEvent(MotionEvent a) {
        float b = a.getX();
        float c = a.getY();
        switch (a.getAction()) {
            case 0:
                a.this.j = b;
                a.this.k = c;
                a.this.i = d();
                break;
            case 1:
                a.this.j = -1.0f;
                a.this.k = -1.0f;
                break;
            case 2:
                if (a.this.j < 0.0f || a.this.k < 0.0f) {
                    return false;
                }
                if (Math.abs(c - a.this.k) <= 50.0f || Math.abs(c - a.this.k) >= 2.0f * Math.abs(b - a.this.j)) {
                    e((int) ((a.this.k - c) / 2.0f));
                    break;
                }
                a.this.j = -1.0f;
                a.this.k = -1.0f;
                return false;
                
        }
        return false;
    }

    private int d() {
        return (int) (getWindow().getAttributes().screenBrightness * 255.0f);
    }

    private void e(int a) {
        int b = this.i + a;
        if (b < 0) {
            b = 0;
        }
        if (b > 255) {
            b = 255;
        }
        this.l = getWindow().getAttributes();
        this.l.screenBrightness = ((float) b) * 0.003921569f;
        getWindow().setAttributes(this.l);
    }
}