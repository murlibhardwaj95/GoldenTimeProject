package com.goldentime.syon.goldentime.classpackage;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ClassBean implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("classId")
    @Expose
    private Integer classId;
    @SerializedName("className")
    @Expose
    private String className;
    @SerializedName("pagesCnt")
    @Expose
    private Integer pagesCnt;
    @SerializedName("activitiesCnt")
    @Expose
    private Integer activitiesCnt;
    @SerializedName("excercisesCnt")
    @Expose
    private Integer excercisesCnt;
    @SerializedName("chaptersCnt")
    @Expose
    private Integer chaptersCnt;
    @SerializedName("classImageHDPI")
    @Expose
    private String classImageHDPI;
    @SerializedName("classImageMDPI")
    @Expose
    private String classImageMDPI;
    @SerializedName("classImageXHDPI")
    @Expose
    private String classImageXHDPI;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getPagesCnt() {
        return pagesCnt;
    }

    public void setPagesCnt(Integer pagesCnt) {
        this.pagesCnt = pagesCnt;
    }

    public Integer getActivitiesCnt() {
        return activitiesCnt;
    }

    public void setActivitiesCnt(Integer activitiesCnt) {
        this.activitiesCnt = activitiesCnt;
    }

    public Integer getExcercisesCnt() {
        return excercisesCnt;
    }

    public void setExcercisesCnt(Integer excercisesCnt) {
        this.excercisesCnt = excercisesCnt;
    }

    public Integer getChaptersCnt() {
        return chaptersCnt;
    }

    public void setChaptersCnt(Integer chaptersCnt) {
        this.chaptersCnt = chaptersCnt;
    }

    public String getClassImageHDPI() {
        return classImageHDPI;
    }

    public void setClassImageHDPI(String classImageHDPI) {
        this.classImageHDPI = classImageHDPI;
    }

    public String getClassImageMDPI() {
        return classImageMDPI;
    }

    public void setClassImageMDPI(String classImageMDPI) {
        this.classImageMDPI = classImageMDPI;
    }

    public String getClassImageXHDPI() {
        return classImageXHDPI;
    }

    public void setClassImageXHDPI(String classImageXHDPI) {
        this.classImageXHDPI = classImageXHDPI;
    }
}
