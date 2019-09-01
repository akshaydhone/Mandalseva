package com.mandalseva.notification.Model;

public  class  Clients{

    private  String amount,date,name;

    public Clients() {
    }

    public Clients(String amount, String date, String name) {
        this.amount = amount;
        this.date = date;
        this.name = name;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}