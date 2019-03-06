package com.goldentime.syon.goldentime.lesson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LessonBean implements Serializable {

    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("chapterId")
    @Expose
    private Integer chapterId;
    @SerializedName("chapterName")
    @Expose
    private String chapterName;
    @SerializedName("chapterNo")
    @Expose
    private Integer chapterNo;
    @SerializedName("seriesId")
    @Expose
    private Integer seriesId;
    @SerializedName("subId")
    @Expose
    private Integer subId;
    @SerializedName("colourName")
    @Expose
    private String colourName;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getChapterId() {
        return chapterId;
    }

    public void setChapterId(Integer chapterId) {
        this.chapterId = chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public Integer getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(Integer chapterNo) {
        this.chapterNo = chapterNo;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public Integer getSubId() {
        return subId;
    }

    public void setSubId(Integer subId) {

        this.subId = subId;
    }

    public String getColourName() {
        return colourName;
    }

    public void setColourName(String colourName) {
        this.colourName = colourName;
    }

    public Integer getnSrNo() {
        return nSrNo;
    }

    public void setnSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

}