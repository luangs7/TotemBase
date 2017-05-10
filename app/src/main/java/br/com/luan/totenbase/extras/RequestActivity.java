package br.com.luan.totenbase.extras;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import br.com.luan.totenbase.extras.dao.LocalDbImplement;
import br.com.luan.totenbase.extras.model.Auth;
import br.com.luan.totenbase.extras.model.Search;
import br.com.luan.totenbase.extras.retrofit.ApiManager;
import br.com.luan.totenbase.extras.retrofit.CustomCallback;
import br.com.luan.totenbase.extras.retrofit.RequestInterfaceUser;
import io.realm.Realm;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import br.com.luan.totenbase.extras.extras.Utils;

/**
 * Created by Luan on 05/05/17.
 */

public class RequestActivity {
    Context context;
    Activity activity;

    public RequestActivity(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void getauth() {
        Auth auth = new Auth();
        auth.setEmail("admin@squarebits.com.br");
        auth.setPassword("@squarebits");
        String json = new Utils().GsonCustomAuth(auth,"auth");
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);


        new ApiManager(context)
                .getRetrofit()
                .create(RequestInterfaceUser.class)
                .requestAuth(body)
                .enqueue(new CustomCallback<Auth>(activity, new CustomCallback.OnResponse<Auth>() {
                    @Override
                    public void onResponse(Auth response) {
                        Log.d("", "onResponse: ");
                        new LocalDbImplement<Auth>(context).save(response);
                        //senddata(context,object,response.getAuth());
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRetry(Throwable t) {
                        getauth();
                    }
                },false));

    }


    public void senddata(String json) {
        Auth auth = new LocalDbImplement<Auth>(context).getDefault(Auth.class);
        if(auth == null){
            getauth();
        }
//        String json = new Utils().GsonCustom(objects,"survey");
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), json);

        Map<String,String> header = new HashMap<>();
        header.put("Authorization", "Token token=" + auth.getAuth());

        new ApiManager(context)
                .getRetrofit()
                .create(RequestInterfaceUser.class)
                .requestSend(header,body)
                .enqueue(new CustomCallback<ResponseBody>(activity, new CustomCallback.OnResponse<ResponseBody>() {
                    @Override
                    public void onResponse(ResponseBody response) {
                        Log.d("", "onResponse: ");
                        Toast.makeText(context, "Sincronizado com sucesso!", Toast.LENGTH_SHORT).show();

                        Realm realm = Realm.getInstance(MyApplication.getInstance().configurationSurvey());
                        realm.beginTransaction();
                        realm.where(Search.class).findAll().deleteAllFromRealm();
                        realm.commitTransaction();
                        context.startActivity(new Intent(context,MainActivity.class));
                        new Utils().clearShared(context);
                        Intent intent = new Intent();
                        intent.setClass(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                        activity.finishAffinity();


                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Toast.makeText(context, "Dados serao sincronizados assim que possível!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRetry(Throwable t) {
                        getauth();
                    }
                },false));

    }
}
