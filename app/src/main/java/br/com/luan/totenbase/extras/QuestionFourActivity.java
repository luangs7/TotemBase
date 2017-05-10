package br.com.luan.totenbase.extras;

import android.os.Bundle;
import android.view.View;

import br.com.luan.totenbase.R;
import br.com.luan.totenbase.extras.model.Questions;
import br.com.luan.totenbase.extras.model.Search;
import io.realm.RealmList;
import br.com.luan.totenbase.extras.extras.Utils;

public class QuestionFourActivity extends BaseActivity implements View.OnClickListener {

    Search search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_four);

        search = new Utils().getShared(this);

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
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question4),getResources().getString(R.string.answer1)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }

    public void onNoClick(){
        RealmList<Questions> questions = search.getQuestionsAttributes();
        questions.add(new Questions(getResources().getString(R.string.question4),getResources().getString(R.string.answer2)));
        search.setQuestionsAttributes(questions);
        new Utils().updateShared(this,search);
    }
}
