package com.example.fuelstation.moduls;

public class benz {

    String type;
    String value;

    public benz() {
    }
    public benz(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getBenztype() {
        return type;
    }

    public String getBenzvalue() {
        return value;
    }

    public void setBenztype(String benztype) {
        this.type = benztype;
    }

    public void setBenzvalue(String benzvalue) {
        this.value = benzvalue;
    }
}
