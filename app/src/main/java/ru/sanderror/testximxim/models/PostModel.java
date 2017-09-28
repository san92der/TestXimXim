package ru.sanderror.testximxim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostModel {

    @SerializedName("message")
    @Expose
    private String mMessage;
    @SerializedName("data")
    @Expose
    private List<ListItem> mData;

    /**
     * @return mData
     */
    public List<ListItem> getData() {
        return mData;
    }

    /**
     * @param mData
     */
    public void setData(List<ListItem> mData) {
        this.mData = mData;
    }
}

