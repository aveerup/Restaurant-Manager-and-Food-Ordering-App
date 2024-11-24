package com.example.oop_project_part2_modified;

import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class foodList implements Serializable {
    private ArrayList<food> foodlist;
    //private int size;
    //private int max_size;
    private int count;

    public foodList(){
        count=1;
        foodlist=new ArrayList<>();
    }

    public food getFood(int i){
        return foodlist.get(i);
    }

    public ArrayList<food> getArrayList(){
        return foodlist;
    }

    public void addFood(food f){
        // foodlist[size]=f;
        // size++;
        // if(size==max_size){
        //     max_size=max_size*(++count);
        //     foodlist=Arrays.copyOf(foodlist,max_size);
        // }
        foodlist.add(f);
    }

    public int getFLSize(){
        return foodlist.size();
    }

    public ArrayList<Integer> searchByName(String Name){
        String temp=new String(Name.toUpperCase());
        ArrayList<Integer> matches=new ArrayList<>();
        for(int i=0; i<foodlist.size();i++){
            if(foodlist.get(i).getName().toUpperCase().contains(temp)){
                matches.add(i);
            }
        }
        return matches;
    }

    public ArrayList<Integer> underRestaurant(int restId){
        ArrayList<Integer> underRest=new ArrayList<>();
        for(int i=0;i<foodlist.size();i++){
            if(foodlist.get(i).getId()==restId){
                underRest.add(i);
            }
        }
        return underRest;
    }

    public ArrayList<Integer> searchByCategory(String Category){
        String temp=new String(Category.toUpperCase());
        ArrayList<Integer> matches=new ArrayList<Integer>();
        for(int i=0;i<foodlist.size();i++){
            if(foodlist.get(i).getCategory().toUpperCase().contains(temp)){
                matches.add(i);
            }
        }
        return matches;
    }

    public ArrayList<Integer> searchByRange(double low, double high){
        ArrayList<Integer> inRange=new ArrayList<>();
        for(int i=0;i<foodlist.size();i++){
            if(foodlist.get(i).getPrice()>= low && foodlist.get(i).getPrice()<=high){
                inRange.add(i);
            }
        }
        return inRange;
    }



    
}
