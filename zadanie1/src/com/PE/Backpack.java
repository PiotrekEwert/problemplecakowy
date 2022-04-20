package com.PE;

import java.util.ArrayList;
import java.util.Random;

public class Backpack {
    private ArrayList<Item> items;
    private int sumWeight = 0;
    private int sumPrice = 0;
    private int maxWeight;

    public Backpack(ArrayList<Item> list, int maxMass){
        items = new ArrayList<>();
        maxWeight=maxMass;
        GenerateRandomBackpack(list);
    }
    public Backpack(int maxMass){
        items = new ArrayList<>();
        maxWeight=maxMass;
    }

    public void GenerateRandomBackpack(ArrayList<Item> list){
        int tries=0;
        Random random = new Random();
        while(true) {
            Item randomItem = list.get(random.nextInt(list.size()));
            if (addItem(randomItem)) {
                tries = 0;
            } else {
                tries++;
            }
            if (tries >= 50) {
                break;
            }
        }
    }

    public boolean addItem(Item item){
        if(sumWeight + item.getWeight() <= maxWeight) {
            sumPrice += item.getPrice();
            sumWeight += item.getWeight();
            items.add(item);
            return true;
        }else{
            return false;
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getSumWeight() {
        sumWeight=0;
        for (Item item: items) {
            sumWeight += item.getWeight();
        }
        return sumWeight;
    }

    public int getSumPrice() {
        return sumPrice;
    }
}
