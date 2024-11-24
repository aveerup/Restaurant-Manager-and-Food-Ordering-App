package com.example.oop_project_part2_modified;

import java.io.Serializable;
import java.util.ArrayList;

public class food implements Serializable {
    private int Id;
    private String Category;
    private String Name;
    private double Price;
    private String restaurantName;

    public food(int Id, String Category, String Name, double Price){
        this.Id=Id;
        this.Category=Category;
        this.Name=Name;
        this.Price=Price;
    }

    public food(food f){
        this.Id=f.Id;
        this.Category=f.Category;
        this.Name=f.Name;
        this.Price=f.Price;
    }

    public void setId(int Id){
        this.Id=Id;
    }    

    public void setCategory(String Category){
        this.Category=Category;
    }    

    public void setName(String Name){
        this.Name=Name;
    }    

    public void setPrice(double Price){
        this.Price=Price;
    }    

    public int getId(){
        return Id;
    }

    public String getCategory(){
        return Category;
    }

    public String getName(){
        return Name;
    }

    public double getPrice(){
        return Price;
    }

    public void setRestaurantName(String restaurantName) { this.restaurantName = restaurantName; }
     public String getRestaurantName(){ return this.restaurantName; };

    public void showDetails(){
        System.out.println("Id: "+Id+","+" Category: "+Category+","+" Name: "+Name+","+" Price: "+Price);
    }

}