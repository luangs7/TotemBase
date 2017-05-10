package br.com.luan.totenbase.extras.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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

    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("kind")
    @Expose
    private String kind;
    @SerializedName("label")
    @Expose
    private String label;
    @PrimaryKey
    private String id;

    public Questions() {
    }

    public Questions(boolean flag) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat codeFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String code = codeFormat.format(new Date());

        this.id = code+label;
    }

    public Questions( String label, String value) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat codeFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String date = dateFormat.format(new Date());
        String code = codeFormat.format(new Date());
        this.id = code+label;
        this.value = value;
        this.kind = "string";
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
