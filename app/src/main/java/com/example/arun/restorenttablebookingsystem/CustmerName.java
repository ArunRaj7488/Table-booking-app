package com.example.arun.restorenttablebookingsystem;

public class CustmerName {
    private String csId;
    private String csName;
    private String csGenre;
    private int csDate;
    private int csHour;

    public CustmerName(String csId, String csName, String csGenre, int csDate, int csHour, int csMin) {
        this.csId = csId;
        this.csName = csName;
        this.csGenre = csGenre;
        this.csDate = csDate;
        this.csHour = csHour;
        this.csMin = csMin;
    }

    public int getCsMin() {
        return csMin;
    }

    public void setCsMin(int csMin) {
        this.csMin = csMin;
    }

    private int csMin;

    public CustmerName(){
    }

    public CustmerName(String csId, String csName, String csGenre, int csDate, int csHour) {
        this.csId = csId;
        this.csName = csName;
        this.csGenre = csGenre;
        this.csDate = csDate;
        this.csHour = csHour;
    }

    public int getCsDate() {
        return csDate;
    }

    public void setCsDate(int csDate) {
        this.csDate = csDate;
    }

    public int getCsHour() {
        return csHour;
    }

    public void setCsHour(int csHour) {
        this.csHour = csHour;
    }

    public String getCsId() {
        return csId;
    }

    public void setCsId(String csId) {
        this.csId = csId;
    }

    public String getCsName() {
        return csName;
    }

    public void setCsName(String csName) {
        this.csName = csName;
    }

    public String getCsGenre() {
        return csGenre;
    }

    public void setCsGenre(String csGenre) {
        this.csGenre = csGenre;
    }

}