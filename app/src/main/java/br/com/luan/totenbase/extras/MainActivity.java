package br.com.luan.totenbase.extras;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.github.rrsystems.utilsplus.android.UtilsPlus;

import java.lang.reflect.Method;

import br.com.luan.totenbase.BuildConfig;
import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.extras.Utils;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.RealmList;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDebugDBAddressLogToast(this);
//        Realm realm = Realm.getInstance(MyApplication.getInstance().configurationSurvey());
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Search search = new Search(true);
//                realm.copyToRealmOrUpdate(search);
//            }
//        });

        //on initview() instanciate the button with clicklistner
        //for example: user btn2.setonclicklistner(this)

        if(UtilsPlus.getInstance().checkNetworkState()) {
            new RequestActivity(this, MainActivity.this).getauth();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btnYes:
//                onYesClick();
//                break;
//            case R.id.btnNo:
//                onNoClick();
//                break;
            default:
                break;
        }
    }

    public void onYesClick(){
        Search search = new Search();
        RealmList<Questions> questions = new RealmList<>();
        questions.add(new Questions(getResources().getString(R.string.question1),"Sim, já sabia."));
        search.setQuestionsAttributes(questions);
        new Utils().setShared(this,search);
     }

     public void onNoClick(){
         Search search = new Search();
         RealmList<Questions> questions = new RealmList<>();
         questions.add(new Questions(getResources().getString(R.string.question1),"Não, ainda não."));
         search.setQuestionsAttributes(questions);
         new Utils().setShared(this,search);
     }

    public static void showDebugDBAddressLogToast(Context context) {
        if (BuildConfig.DEBUG) {
            try {
                Class<?> debugDB = Class.forName("com.amitshekhar.DebugDB");
                Method getAddressLog = debugDB.getMethod("getAddressLog");
                Object value = getAddressLog.invoke(null);
                UtilsPlus.getInstance().put("url_debug",(String) value);
//                UtilsPlus.getInstance().toast((String) value,10);
            } catch (Exception ignore) {

            }
        }
    }
}
