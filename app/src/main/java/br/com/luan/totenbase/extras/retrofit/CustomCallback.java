package br.com.luan.totenbase.extras.retrofit;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;

import br.com.luan.totenbase.R;
import retrofit2.Call;
import retrofit2.Callback;


/**
 * Created by luan on 7/11/16.
 */
public  class CustomCallback<T> implements Callback<T> {
    Context context;
    ProgressDialog dialog;
    OnResponse onResponse;
    View viewLayout;

    public CustomCallback(Context context,OnResponse<T> onResponse){
        this.context = context;
        dialog = new ProgressDialog(context, R.style.AppTheme_AlertDialog);
        dialog.setCancelable(false);
        dialog.setMessage("Buscando dados...");
        dialog.show();
        this.onResponse = onResponse;
    }

        public CustomCallback(Context context, View viewLayout,OnResponse<T> onResponse){
        this.context = context;
        dialog = new ProgressDialog(context, R.style.AppTheme_AlertDialog);
            dialog.setCancelable(false);
            dialog.setMessage("Buscando dados...");
            dialog.show();
        this.onResponse = onResponse;
        this.viewLayout = viewLayout;
    }

    public CustomCallback(Activity context, OnResponse<T> onResponse, View viewLayout){
        this(context,onResponse,true);
        this.viewLayout = viewLayout;
    }

    public CustomCallback(Activity context, OnResponse<T> onResponse, boolean loadDialog){
        this.context = context;
        if(loadDialog){
            try {
                dialog = new ProgressDialog(context, R.style.AppTheme_AlertDialog);
                dialog.setCancelable(false);
                dialog.setMessage("Buscando dados...");
                dialog.show();
            }catch (Throwable ex){
                Log.e("dialog", "CustomCallback: ",ex);
            }
        }else{

        }

        this.onResponse = onResponse;
    }


    public CustomCallback(Context context,String dialogText, View viewLayout,OnResponse<T> onResponse) {
        this.context = context;
        dialog = new ProgressDialog(context, R.style.AppTheme_AlertDialog);
        dialog.setCancelable(false);
        dialog.setMessage(dialogText);
        dialog.show();
        this.onResponse = onResponse;
        this.viewLayout = viewLayout;
    }

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        String error = "";
    try {
        dialog.dismiss();
    }catch (Exception e){

    }
        if(response.isSuccessful())
            onResponse.onResponse(response.body());
        else{
            try {
                error = response.errorBody().string();
                onResponse.onFailure(new Throwable(error));
            }catch (Exception ex){
                onResponse.onFailure(new Throwable("Ocorreu um erro"));
            }
        }
    }

    @Override
    public void onFailure(Call<T> call, final Throwable t) {
        if(dialog != null)
            dialog.dismiss();
        if(viewLayout != null){
            Snackbar snackbar = Snackbar
                .make(viewLayout, "Problema de conexao", Snackbar.LENGTH_LONG)
                    .setAction("Tentar novamente", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onResponse.onRetry(t);
                        }
                    });
                snackbar.show();
        }else {
            //Cria o gerador do AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppTheme_AlertDialog);
            //define o titulo
                builder.setTitle("Problema de conexao");
            //define a mensagem
            builder.setMessage("Gostaria de tentar novamente");
            //define um botão como positivo
            builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                    onResponse.onRetry(t);
                }
            });
            //define um botão como negativo.
            builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    arg0.dismiss();
                    onResponse.onFailure(t);
                }
            });
            //cria o AlertDialog
            AlertDialog alerta = builder.create();
            //Exibe
            alerta.show();
        }
    }

    public void useSnackBar(View view){
        viewLayout = view;
    }

    public interface OnResponse<T>{
        public void onResponse(T response);
        public void onFailure(Throwable t);
        public void onRetry(Throwable t);
    }




}