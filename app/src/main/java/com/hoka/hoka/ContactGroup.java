package com.hoka.hoka;

import java.util.ArrayList;

public class ContactGroup {
    //Имя группы
    private String name;
    //Список контактов
    private ArrayList<ContactItem> contacts;

    public ContactGroup(String name){
        this.name = name;
        this.contacts = new ArrayList<ContactItem>();
    }

    public void addContact(ContactItem item){
        contacts.add(item);
    }

    public String getName(){
        return name;
    }

    public ArrayList<ContactItem>getContactsList(){return contacts;}
    public ContactItem getContactItem(int position){
        return contacts.get(position);
    }

}