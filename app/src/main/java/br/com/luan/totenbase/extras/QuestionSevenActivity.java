package br.com.luan.totenbase.extras;

import android.os.Bundle;
import android.view.View;

import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.extras.Utils;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.RealmList;

public class QuestionSevenActivity extends BaseActivity implements View.OnClickListener {

    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_seven);

        search = new Utils().getShared(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btnExc:
//                onFirstClick();
//                break;
//            case R.id.btnNormal:
//                onSecondClick();
//                break;
//            case R.id.btnBad
//                onThirdClick();
            default:
                break;
        }
    }

    public void onFirstClick() {
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question8), getResources().getString(R.string.answer7)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this, search);
    }

    public void onSecondClick() {
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question8), getResources().getString(R.string.answer8)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this, search);
    }

    public void onThirdClick() {
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question8), getResources().getString(R.string.answer9)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this, search);
    }
}
