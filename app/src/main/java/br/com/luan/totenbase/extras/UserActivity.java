package br.com.luan.totenbase.extras;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;

import br.com.luan.totenbase.R;

public class UserActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btnParticipar:
//                break;
//            case R.id.btnCancel:
//                break;
            default:
                break;
        }
    }

    public void onCancelPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deseja cancelar a avaliaçao?");
        builder.setMessage("Ao cancelar a avaliaçao, voce deixa de participar dos sorteio de premios. Deseja cancelar?")
                .setPositiveButton("Sim! Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity

                    }
                })
                .setNegativeButton("Nao, continuar preenchendo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
