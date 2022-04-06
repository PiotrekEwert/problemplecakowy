package com.PE;

public class Item {
    int id;
    int price;
    int weight;
    public Item(int id, int weight, int price){
        this.id = id;
        this.price = price;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
    public void Print(){
        System.out.println("Item:\n"
                + "id: " + getId() + "\n"
                + "weight: " + getWeight() + "\n"
                + "price: " + getPrice() + "\n"
        );
    }
}
