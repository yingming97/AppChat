package net.fpt.appchat.model;

import java.io.Serializable;

public class User implements Serializable {
    private String email, pass, hoTen,uid;
    public static final String TB_NAME = "tb_user";
    public static final String COL_EMAIL = "email";
    public static final String COL_PASS = "pass";
    public static final String COL_HO_TEN = "hoTen";
    public static final String COL_COL_UID = "uid";

    public User() {
    }

    public User(String email, String pass, String hoTen, String uid) {
        this.email = email;
        this.pass = pass;
        this.hoTen = hoTen;
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
