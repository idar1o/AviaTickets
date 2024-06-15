package com.example.myappavia.data.API.ModelPlace;

import com.google.gson.annotations.SerializedName;

public class CountryCases {
    @SerializedName("vi")
    private String vi;
    @SerializedName("tv")
    private String tv;
    @SerializedName("su")
    private String su;
    @SerializedName("ro")
    private String ro;
    @SerializedName("pr")
    private String pr;
    @SerializedName("da")
    private String da;

    public CountryCases(String vi, String tv, String su, String ro, String pr, String da) {
        this.vi = vi;
        this.tv = tv;
        this.su = su;
        this.ro = ro;
        this.pr = pr;
        this.da = da;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }

    public String getTv() {
        return tv;
    }

    public void setTv(String tv) {
        this.tv = tv;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getRo() {
        return ro;
    }

    public void setRo(String ro) {
        this.ro = ro;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getDa() {
        return da;
    }

    public void setDa(String da) {
        this.da = da;
    }
}
