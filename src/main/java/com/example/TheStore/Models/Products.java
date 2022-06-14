package com.example.TheStore.Models;

public class Products {
    public int id;
    public String name;

    public int price;
    public int quantity;

    public Products(Integer id, String name, int price, int quantity) {
        this.id = id;
        this.name = name;

        this.price = price;
        this.quantity = quantity;
    }

    public Products(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int name) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

