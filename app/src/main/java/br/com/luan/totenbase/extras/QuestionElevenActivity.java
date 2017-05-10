package br.com.luan.totenbase.extras;

import android.os.Bundle;
import android.view.View;

import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.extras.Utils;
import br.com.luan.totenbase.extras.model.Options;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.RealmList;

public class QuestionElevenActivity extends BaseActivity {

    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_eleven);

        search = new Utils().getShared(this);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Dptos dpto = (Dptos) parent.getItemAtPosition(position);
//                onItemTap(view, dpto.getTitle(),position);
//            }
//        });
    }

    public void onItemTap(View v, Options option){
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question5),option.getTitle()));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }
}
