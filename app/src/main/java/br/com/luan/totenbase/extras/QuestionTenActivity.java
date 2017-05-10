package br.com.luan.totenbase.extras;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import br.com.luan.totenbase.extras.extras.Utils;

import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.model.Options;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.RealmList;

public class QuestionTenActivity extends BaseActivity {

    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_ten);

        search = new Utils().getShared(this);

        // value of edittext
    }

    public void onSubmit(View v, Options option){
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question11),option.getTitle()));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }

    public void onEmptyValue(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Poderia descrever o motivo?");
        builder.setMessage("Voce ainda nao confirmou o motivo. Deseja continuar?")
                .setPositiveButton("Sim! Continuar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the positive button event back to the host activity

                    }
                })
                .setNegativeButton("Nao, queria informar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Send the negative button event back to the host activity
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
