package com.hoka.hoka;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class MySqlIncident implements Serializable {
    @SerializedName("pid")
    public int pid;
    @SerializedName("name")
    public String name;
    @SerializedName("phone")
    public String phone;
    @SerializedName("address")
    public String address;
    @SerializedName("about")
    public String about;
    @SerializedName("number_assets")
    public String number_assets;
    @SerializedName("status")
    public int status;
    @SerializedName("service")
    public int service;
    @SerializedName("comment")
    public String comment;
}
