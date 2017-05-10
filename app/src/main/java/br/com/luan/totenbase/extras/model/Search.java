package br.com.luan.totenbase.extras.model;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
    @SerializedName("device_id")
    @Expose
    private Integer deviceId;
    @SerializedName("code")
    @Expose
    @PrimaryKey
    private String code;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("questions_attributes")
    @Expose
    private RealmList<Questions> questionsAttributes;
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Search() {

    }

    public Search(boolean flag) {
        init();
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RealmList<Questions> getQuestionsAttributes() {
        return questionsAttributes;
    }

    public void setQuestionsAttributes(RealmList<Questions> questionsAttributes) {
        this.questionsAttributes = questionsAttributes;
    }

    private void init() {
        Log.i("SQBITS", "survey.init");


        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat codeFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String code = codeFormat.format(new Date());

        this.code = code;
        this.date = date;
        this.deviceId = 1;

    }
}
