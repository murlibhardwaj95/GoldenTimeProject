package com.goldentime.syon.goldentime.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Choice implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("qcId")
    @Expose
    private Integer qcId;
    @SerializedName("qChoice")
    @Expose
    private String qChoice;
    private final static long serialVersionUID = -3672913836337317819L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getQcId() {
        return qcId;
    }

    public void setQcId(Integer qcId) {
        this.qcId = qcId;
    }

    public String getQChoice() {
        return qChoice;
    }

    public void setQChoice(String qChoice) {
        this.qChoice = qChoice;
    }
}
