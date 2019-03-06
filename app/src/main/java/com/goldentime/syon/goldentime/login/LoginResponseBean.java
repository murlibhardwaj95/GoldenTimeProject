package com.goldentime.syon.goldentime.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginResponseBean implements Serializable {

    @SerializedName("lUser_IdNo")
    @Expose
    private Integer lUserIdNo;
    @SerializedName("sUser_Login")
    @Expose
    private String sUserLogin;
    @SerializedName("sUser_Pwd")
    @Expose
    private String sUserPwd;
    @SerializedName("nActive")
    @Expose
    private Integer nActive;
    @SerializedName("sToken_No")
    @Expose
    private String sTokenNo;
    @SerializedName("dtMod_Date")
    @Expose
    private String dtModDate;
    @SerializedName("dtCreate_Date")
    @Expose
    private String dtCreateDate;

    public Integer getLUserIdNo() {
        return lUserIdNo;
    }

    public void setLUserIdNo(Integer lUserIdNo) {
        this.lUserIdNo = lUserIdNo;
    }

    public String getSUserLogin() {
        return sUserLogin;
    }

    public void setSUserLogin(String sUserLogin) {
        this.sUserLogin = sUserLogin;
    }

    public String getSUserPwd() {
        return sUserPwd;
    }

    public void setSUserPwd(String sUserPwd) {
        this.sUserPwd = sUserPwd;
    }

    public Integer getNActive() {
        return nActive;
    }

    public void setNActive(Integer nActive) {
        this.nActive = nActive;
    }

    public String getSTokenNo() {
        return sTokenNo;
    }

    public void setSTokenNo(String sTokenNo) {
        this.sTokenNo = sTokenNo;
    }

    public String getDtModDate() {
        return dtModDate;
    }

    public void setDtModDate(String dtModDate) {
        this.dtModDate = dtModDate;
    }

    public String getDtCreateDate() {
        return dtCreateDate;
    }

    public void setDtCreateDate(String dtCreateDate) {
        this.dtCreateDate = dtCreateDate;
    }

}
