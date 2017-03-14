package com.hoka.hoka;

public class ContactItem {
    private int id;
    private int pid;
    private String name;
    private String phone;
    private String about;
    private String address;
    private int status;
    private int service;
    private String number_assets;
    private String comment;


    public ContactItem(int id, int pid, String name, String phone, String about, String address, int status,
                       int service, String number_assets, String comment){
        this.id = id;
        this.pid = pid;
        this.name = name;
        this.phone = phone;
        this.about = about;
        this.address = address;
        this.status = status;
        this.service = service;
        this.number_assets = number_assets;
        this.comment = comment;
    }

    public ContactItem(int pid, String name, String phone, String about, String address, int status,
                       int service, String number_assets, String comment){
        this.pid = pid;
        this.name = name;
        this.phone = phone;
        this.about = about;
        this.address = address;
        this.status = status;
        this.service = service;
        this.number_assets = number_assets;
        this.comment = comment;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public int getPid(){return pid;}
    public void setPid(int pid){this.pid = pid;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getAbout(){return about;}
    public void setAbout(String about){this.about = about;}
    public String getAddress(){return address;}
    public void setAddress(String address){this.address = about;}
    public int getStatus(){return status;}
    public void setStatus(int status){this.status = status;}
    public int getService(){return service;}
    public void setService(int service){this.service = service;}
    public String getNumber_assets(){return number_assets;}
    public void setNumber_assets(String number_assets) {this.number_assets = number_assets;}
    public String getComment(){return comment;}
    public void setComment(String comment) {this.comment = comment;}

    public static ContactItem selectedItem;
}