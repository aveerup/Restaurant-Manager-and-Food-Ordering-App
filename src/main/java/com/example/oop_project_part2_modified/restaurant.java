package com.example.oop_project_part2_modified;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class restaurant implements Serializable {
    private int Id;
    private String Name;
    private double Score;
    private String Price;
    private String zipCode;
    private String[] Categories;
    private foodList menu;

    public restaurant(int Id, String Name, double Score,String Price, String zipCode, String[] Categories){
        this.Id=Id;
        this.Name=Name;
        this.Score=Score;
        this.Price=Price;
        this.zipCode=zipCode;
        this.Categories=new String[3];
        this.Categories=Arrays.copyOf(Categories, 3);
    }

    public restaurant(int Id, String Name, double Score,String Price, String zipCode, String[] Categories,foodList menu){
        this.Id=Id;
        this.Name=Name;
        this.Score=Score;
        this.Price=Price;
        this.zipCode=zipCode;
        this.Categories=new String[3];
        this.Categories=Arrays.copyOf(Categories, 3);
        this.menu=menu;
    }

    public restaurant(restaurant r){
        this.Id=r.Id;
        this.Name=r.Name;
        this.Score=r.Score;
        this.Price=r.Price;
        this.zipCode=r.zipCode;
        this.Categories=r.Categories;
        this.menu=r.menu;
    }

    // setter functions

    public void setId(int Id){
        this.Id=Id;
    }

   public void setName(String Name){
        this.Name=Name;
    }

    public void setScore(double Score){
        this.Score=Score;
    }

    public void setPrice(String Price){
        this.Price=Price;
    }

    public void setzipCode(String zipCode){
        this.zipCode=zipCode;
    }

    public void setCategories(String[] Categories){
        this.Categories=Arrays.copyOf(Categories, 3);
    }

    // getter functions

    public int getId(){
        return this.Id;
    }

    public String getName(){
        return this.Name;
    }

    public double getScore(){
        return this.Score;
    }

    public String getPrice(){
        return this.Price;
    }

    public String getzipCode(){
        return this.zipCode;
    }

    public String[] getCategories(){
        return this.Categories;
    }

    //public getMenuFoodL
    public ArrayList<food> getMenuArrayList(){ return menu.getArrayList(); }

    public void showDetails(){
        System.out.println("Id: "+Id+", Name: "+Name+", Score: "+Score+", Price: "+Price+", Zipcode: "+zipCode+", Categories: "+Categories[0]+", "+Categories[1]+", "+Categories[2]);
    }
}
