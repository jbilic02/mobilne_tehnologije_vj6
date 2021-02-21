package com.example.app;

public class UserData {
    String id,godina, ime, predavac;
    public UserData(String id, String godina,String ime, String predavac){
        this.id = id;
        this.godina = godina;
        this.ime = ime;
        this.predavac = predavac;

    }

    public UserData() {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGodina() {
        return godina;
    }

    public void setGodina(String godina) {
        this.godina = godina;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPredavac() {
        return predavac;
    }

    public void setPredavac(String predavac) {
        this.predavac = predavac;
    }
}
