package com.PE;

import com.sun.org.apache.bcel.internal.util.BCELifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Zadanie {
    ArrayList<Item> list;
    int HMS = 10;
    ArrayList<Backpack> HM;
    int maximumBackpackWeight = 500;
    public Zadanie() throws FileNotFoundException {
        HM = new ArrayList<>();
        list = new ArrayList<Item>();
        File f = new File("dane.csv");
        Scanner r = new Scanner(f);
        while (r.hasNextLine()) {
            String line = r.nextLine();
            int id = Integer.parseInt(line.substring(0,line.indexOf(';')));
            line=line.substring(line.indexOf(';')+1);
            int weight = Integer.parseInt(line.substring(0,line.indexOf(';')));
            line=line.substring(line.indexOf(';')+1);
            int price = Integer.parseInt(line);
            list.add(new Item(id,weight,price));
        }

        for(int i=0;i<10;i++){
            HM.add(new Backpack(list,maximumBackpackWeight));
        }

        System.out.println("10 losowych plecaków:");
        int i=1;
        for (Backpack b : HM){
            System.out.println(i++ + ": Waga= "+b.getSumWeight() + " Wartość= "+ b.getSumPrice());
        }

        int iterations=100000;
        int HMCR=70;
        Random random=new Random();
        for(int j=0;j<iterations;j++){
            int r1=random.nextInt(100)+1;
            if(r1<HMCR){
                addNewBackpack(takeFromHM());
            }else{
                addNewBackpack(takeFromList());
            }
        }

        System.out.println("10 plecaków po poprawianiu:");
        i=1;
        for (Backpack b : HM){
            System.out.println(i++ + ": Waga= "+b.getSumWeight() + " Wartość= "+ b.getSumPrice());
        }
    }

    private void addNewBackpack(Backpack b){
        int x=0,y=0;
        boolean foundCheaperBackpack=false;
        y=b.getSumPrice();
        for (Backpack bp : HM) {
            if(bp.getSumPrice()<y){
                foundCheaperBackpack=true;
                y=bp.getSumPrice();
                x=HM.indexOf(bp);
            }
        }
        if(foundCheaperBackpack){
            HM.remove(x);
            HM.add(b);
        }

    }

    private Backpack takeFromHM(){
        Backpack b = new Backpack(maximumBackpackWeight);
        Random random = new Random();
        int maxBackpackSize=0;

        for (Backpack bp : HM){
            if(bp.getItems().size()>maxBackpackSize) maxBackpackSize=bp.getItems().size();
        }

        for(int i =0;i<maxBackpackSize;i++){
            int randomBackpack = random.nextInt(HM.size());
            try{
                b.addItem(HM.get(randomBackpack).getItems().get(i));
            }catch (IndexOutOfBoundsException ignored){ }

        }

        int par =20;
        if(random.nextInt(100)+1 >par) return b;
        b.getItems().remove(random.nextInt(b.getItems().size()));
        Item itemToAdd;
        do{
            itemToAdd=list.get(random.nextInt(list.size()));
        }while(itemToAdd.getWeight()+b.getSumWeight()>maximumBackpackWeight);
        b.addItem(itemToAdd);
        return b;
    }
    private Backpack takeFromList(){
        return new Backpack(list,maximumBackpackWeight);
    }
}
