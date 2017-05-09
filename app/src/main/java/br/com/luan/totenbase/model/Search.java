package br.com.luan.totenbase.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Luan on 09/05/17.
 */
@RealmClass
public class Search implements RealmModel {
    @PrimaryKey
    private String id;
    private RealmList<Questions> questionsList;
    private User user;

    public Search() {
    }

    public Search(boolean init) {
        init();
    }

    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat codeFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String code = codeFormat.format(new Date());
        this.id = code;
    }

    public RealmList<Questions> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(RealmList<Questions> questionsList) {
        this.questionsList = questionsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
