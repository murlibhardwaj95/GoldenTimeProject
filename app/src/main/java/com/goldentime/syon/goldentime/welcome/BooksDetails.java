package com.goldentime.syon.goldentime.welcome;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BooksDetails implements Serializable {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<BookDetailsBean> data = null;
    private final static long serialVersionUID = -6701259744232661202L;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BookDetailsBean> getData() {
        return data;
    }

    public void setData(List<BookDetailsBean> data) {
        this.data = data;
    }
}
