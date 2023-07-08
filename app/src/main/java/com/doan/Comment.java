package com.doan;

import java.util.Date;

public class Comment {
    String user;
    String maDD;
    String tg;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTg() {
        return tg;
    }

    public void setTg(String tg) {
        this.tg = tg;
    }

    public Date getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Date thoiGian) {
        this.thoiGian = thoiGian;
    }

    Date thoiGian = new Date();
    String cmt;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMaDD() {
        return maDD;
    }

    public void setMaDD(String maDD) {
        this.maDD = maDD;
    }




    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public Comment(String user, String maDD, String tg, int id, String cmt) {
        this.user = user;
        this.maDD = maDD;
        this.tg = tg;
        this.id = id;
        this.cmt = cmt;
    }

    public Comment(String user, String maDD, String tg, String cmt) {
        this.user = user;
        this.maDD = maDD;
        this.tg = tg;
        this.cmt = cmt;
    }

    public Comment(String user, String maDD, Date thoiGian, String cmt) {
        this.user = user;
        this.maDD = maDD;
        this.thoiGian = thoiGian;
        this.cmt = cmt;
    }

    public Comment() {
    }
}
