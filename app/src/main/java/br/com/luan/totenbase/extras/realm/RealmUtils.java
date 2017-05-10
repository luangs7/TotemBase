package br.com.luan.totenbase.extras.realm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.github.rrsystems.utilsplus.android.UtilsPlus;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.totenbase.extras.MainActivity;
import br.com.luan.totenbase.extras.MyApplication;
import br.com.luan.totenbase.extras.RequestActivity;
import br.com.luan.totenbase.extras.extras.CustomGsonAdapter;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;


/**
 * Created by Luan on 09/05/17.
 */

public class RealmUtils {
    private Context context;
    private Activity activity;

    Realm realm;
    public void saveToRealm(final Search survey){
        realm = Realm.getInstance(MyApplication.getInstance().configurationSurvey());

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(survey);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                preparedata();
            }
        });

    }

    List<Object> objects = new ArrayList<>();

    public void preparedata(){
        if(UtilsPlus.getInstance().checkNetworkState()){
            Realm realmq = Realm.getInstance(MyApplication.getInstance().configurationSurvey());
            RealmResults<Search> results = realmq.where(Search.class).findAll();
            addElements(new Search(),results);
            realmq.close();

            //new RequestActivity(this,FinishActivity.this).senddata(objects);
        }else{
            Toast.makeText(context, "Seus dados serão sincronizados quando houver internet!", Toast.LENGTH_SHORT).show();
            redirect();
        }
    }

    private void addElements(Search object, RealmResults<Search> queryListName){
        for(int i=0; i < queryListName.size(); i++){
            object = new Search();
            RealmList<Questions> questionsAttributeList = new RealmList<>();
            object.setCode(queryListName.get(i).getCode());
            object.setDate(queryListName.get(i).getDate());
            object.setDeviceId(queryListName.get(i).getDeviceId());
            object.setQuestionsAttributes(queryListName.get(i).getQuestionsAttributes());
            for(Questions questionsAttribute : object.getQuestionsAttributes()){
                Questions obj = new Questions();
                obj.setKind(questionsAttribute.getKind());
                obj.setValue(questionsAttribute.getValue());
                obj.setLabel(questionsAttribute.getLabel());
                questionsAttributeList.add(obj);
            }
            object.setQuestionsAttributes(questionsAttributeList);
            objects.add(object);
        }
        new RequestActivity(context,activity).senddata(new CustomGsonAdapter().serialize(objects,"surveys","survey"));

    }

    public void redirect(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
                activity.finish();
            }
        }, 3000);
    }
}
