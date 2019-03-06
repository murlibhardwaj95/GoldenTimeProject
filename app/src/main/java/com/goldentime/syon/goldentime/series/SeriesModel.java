package com.goldentime.syon.goldentime.series;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SeriesModel implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<SeriesBean> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SeriesBean> getData() {
        return data;
    }

    public void setData(List<SeriesBean> data) {
        this.data = data;
    }
}
