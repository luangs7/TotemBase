package br.com.luan.totenbase;

import android.content.Context;
import android.os.Bundle;

import com.github.rrsystems.utilsplus.android.UtilsPlus;

import java.lang.reflect.Method;

import br.com.luan.totenbase.model.Search;
import io.realm.Realm;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDebugDBAddressLogToast(this);
        Realm realm = Realm.getInstance(MyApplication.getInstance().configurationSurvey());
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Search search = new Search(true);
                realm.copyToRealmOrUpdate(search);
            }
        });


    }

    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                UtilsPlus.getInstance().toast((String) value,10);
            } catch (Exception ignore) {

            }
        }
    }
}
