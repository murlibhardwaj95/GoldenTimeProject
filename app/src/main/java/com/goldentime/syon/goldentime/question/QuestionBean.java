package com.goldentime.syon.goldentime.question;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class QuestionBean implements Serializable {

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("qId")
    @Expose
    private Integer qId;
    @SerializedName("qHeadText")
    @Expose
    private String qHeadText;
    @SerializedName("qNo")
    @Expose
    private Integer qNo;
    @SerializedName("qText")
    @Expose
    private String qText;
    @SerializedName("qAns")
    @Expose
    private String qAns;
    @SerializedName("pageNo")
    @Expose
    private Integer pageNo;
    @SerializedName("classId")
    @Expose
    private Integer classId;
    @SerializedName("options")
    @Expose
    private List<Choice> options = null;
    private final static long serialVersionUID = -3823704692968343427L;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getQId() {
        return qId;
    }

    public void setQId(Integer qId) {
        this.qId = qId;
    }

    public String getQHeadText() {
        return qHeadText;
    }

    public void setQHeadText(String qHeadText) {
        this.qHeadText = qHeadText;
    }

    public Integer getQNo() {
        return qNo;
    }

    public void setQNo(Integer qNo) {
        this.qNo = qNo;
    }

    public String getQText() {
        return qText;
    }

    public void setQText(String qText) {
        this.qText = qText;
    }

    public String getQAns() {
        return qAns;
    }

    public void setQAns(String qAns) {
        this.qAns = qAns;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public List<Choice> getOptions() {
        return options;
    }

    public void setOptions(List<Choice> options) {
        this.options = options;
    }
}
