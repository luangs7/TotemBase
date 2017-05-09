package br.com.luan.totenbase.extras;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.luan.totenbase.MyApplication;
import io.realm.Realm;


/**
 * Created by Luan on 09/05/17.
 */

public class RealmUtils {
    private Context context;
    private Activity activity;

    Realm realm;
    public void saveToRealm(final Object survey){
        realm = Realm.getInstance(MyApplication.getInstance().configurationSurvey());

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                realm.copyToRealm(survey);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                preparedata();
            }
        });

//        realm.close();
    }

    List<Object> objects = new ArrayList<>();
    public void preparedata(){
        if(MyApplication.existeConexao(context)){
            Realm realmq = Realm.getInstance(MyApplication.getInstance().configurationSurvey());
            realmq.close();
//            realm.close();
            //new RequestActivity(this,FinishActivity.this).senddata(objects);
        }else{
            Toast.makeText(context, "Seus dados serão sincronizados quando houver internet!", Toast.LENGTH_SHORT).show();
//            redirect();
        }
    }

//    private void addElements(Object object, RealmResults<Survey> queryListName){
//        for(int i=0; i < queryListName.size(); i++){
//            //object = new Survey();
////            RealmList<QuestionsAttribute> questionsAttributeList = new RealmList<>();
////            object.setCode(queryListName.get(i).getCode());
////            object.setDate(queryListName.get(i).getDate());
////            object.setDeviceId(queryListName.get(i).getDeviceId());
////            object.setQuestionsAttributes(queryListName.get(i).getQuestionsAttributes());
////            for(QuestionsAttribute questionsAttribute : object.getQuestionsAttributes()){
////                QuestionsAttribute obj = new QuestionsAttribute();
////                obj.setKind(questionsAttribute.getKind());
////                obj.setValue(questionsAttribute.getValue());
////                obj.setLabel(questionsAttribute.getLabel());
////                questionsAttributeList.add(obj);
////            }
////            object.setQuestionsAttributes(questionsAttributeList);
//            objects.add(object);
//        }
////        new RequestActivity(this,activity).senddata(new Utils().GsonCustom(objects,"survey"));
//
//    }
}
