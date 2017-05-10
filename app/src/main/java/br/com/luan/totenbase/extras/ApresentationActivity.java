package br.com.luan.totenbase.extras;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import br.com.luan.totenbase.R;

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
