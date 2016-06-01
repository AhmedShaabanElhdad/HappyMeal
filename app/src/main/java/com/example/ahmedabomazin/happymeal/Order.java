package com.example.ahmedabomazin.happymeal;

/**
 * Created by Ahmed AboMazin on 5/20/2016.
 */
public class Order
{
    private String id;
    private String type;
    private String address;
    private String date;
    private String price;

    public Order(String id,String type,String address,String date,String price)
    {
        this.id=id;
        this.type=type;
        this.address=address;
        this.date=date;
        this.price=price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
