package br.com.luan.totenbase;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import br.com.luan.totenbase.extras.Utils;

/**
 * Created by Luan on 09/05/17.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        new Utils(getWindow().getDecorView()).threadCheck();
        new Utils(getWindow().getDecorView()).hideSystemUI();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new Utils(getWindow().getDecorView()).hideSystemUI();
        new Utils(getWindow().getDecorView()).threadCheck();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyApplication.getInstance().refresh();
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        MyApplication.getInstance().refresh();
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        MyApplication.getInstance().refresh();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        MyApplication.getInstance().refresh();
        return super.dispatchKeyEvent(event);
    }


}
