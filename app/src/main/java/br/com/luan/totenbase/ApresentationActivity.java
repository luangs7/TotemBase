package br.com.luan.totenbase;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

public class ApresentationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentation);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        MyApplication.getInstance().refresh();
        startActivity(new Intent(getBaseContext(),MainActivity.class));
        return super.dispatchTouchEvent(ev);
    }
}
