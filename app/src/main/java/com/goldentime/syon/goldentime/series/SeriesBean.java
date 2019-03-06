package com.goldentime.syon.goldentime.series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeriesBean implements Serializable {
    @SerializedName("nSrNo")
    @Expose
    private Integer nSrNo;
    @SerializedName("seriesId")
    @Expose
    private Integer seriesId;
    @SerializedName("seriesName")
    @Expose
    private String seriesName;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("seriesImageHDPI")
    @Expose
    private String seriesImageHDPI;
    @SerializedName("seriesImageMDPI")
    @Expose
    private String seriesImageMDPI;
    @SerializedName("seriesImageXHDPI")
    @Expose
    private String seriesImageXHDPI;

    public Integer getNSrNo() {
        return nSrNo;
    }

    public void setNSrNo(Integer nSrNo) {
        this.nSrNo = nSrNo;
    }

    public Integer getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(Integer seriesId) {
        this.seriesId = seriesId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeriesImageHDPI() {
        return seriesImageHDPI;
    }

    public void setSeriesImageHDPI(String seriesImageHDPI) {
        this.seriesImageHDPI = seriesImageHDPI;
    }

    public String getSeriesImageMDPI() {
        return seriesImageMDPI;
    }

    public void setSeriesImageMDPI(String seriesImageMDPI) {
        this.seriesImageMDPI = seriesImageMDPI;
    }

    public String getSeriesImageXHDPI() {
        return seriesImageXHDPI;
    }

    public void setSeriesImageXHDPI(String seriesImageXHDPI) {
        this.seriesImageXHDPI = seriesImageXHDPI;
    }
}
