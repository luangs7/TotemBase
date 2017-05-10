package br.com.luan.totenbase.extras;

import android.os.Bundle;
import android.view.View;

import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.RealmList;
import br.com.luan.totenbase.extras.extras.Utils;

public class QuestionSixActivity extends BaseActivity implements View.OnClickListener {

    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);

        search = new Utils().getShared(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.btnExc:
//                onExcClick();
//                break;
//            case R.id.btnNormal:
//                onNormalClick();
//                break;
//            case R.id.btnBad
//                onBadClick();
            default:
                break;
        }
    }

    public void onExcClick(){
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question7),getResources().getString(R.string.answer7)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }

    public void onNormalClick(){
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question7),getResources().getString(R.string.answer8)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }

    public void onBadClick(){
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question7),getResources().getString(R.string.answer9)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }
}
