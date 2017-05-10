package br.com.luan.totenbase.extras;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.extras.Utils;
import br.com.luan.totenbase.extras.realm.RealmUtils;


public class FinishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        new RealmUtils().saveToRealm(new Utils().getShared(this));
    }



}
