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
public class User implements RealmModel {
    @PrimaryKey
    private String id;
    private String nome;
    private String company;
    private String cargo;
    private String phone;
    private String email;

    public User() {
    }

    public User(boolean init) {
        init();
    }

    private void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat codeFormat = new SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault());
        String code = codeFormat.format(new Date());
        this.id = code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
