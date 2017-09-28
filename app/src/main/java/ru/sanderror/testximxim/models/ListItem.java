package ru.sanderror.testximxim.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ListItem{

    @SerializedName("url")
    @Expose
    private String mUrl;
    @SerializedName("title")
    @Expose
    private String mTitle;

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }
}
