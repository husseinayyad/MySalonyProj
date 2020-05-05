package com.exm.example.mysalonyproj;


public class Salonat {
    String description;
    String image;
    String subcategory;
    String price;
    String data;
    String time;
    String id;
    String type;
    String owner;
    String name ;
    String phone ;
    String address;

    String city ;

    public Salonat() {
    }

    public Salonat(String description, String image, String subcategory, String price, String data, String time, String id, String type, String owner, String name, String phone, String address, String city) {
        this.description = description;
        this.image = image;
        this.subcategory = subcategory;
        this.price = price;
        this.data = data;
        this.time = time;
        this.id = id;
        this.type = type;
        this.owner = owner;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
