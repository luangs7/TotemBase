package br.com.luan.totenbase.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Luan on 09/05/17.
 */

@RealmClass
public class Questions implements RealmModel{
    private String question;
    private String answer;
    @PrimaryKey
    private String id;

    public Questions(boolean init) {
        init();
    }

    public Questions() {

    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat codeFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String code = codeFormat.format(new Date());
        this.id = code;
    }

}
