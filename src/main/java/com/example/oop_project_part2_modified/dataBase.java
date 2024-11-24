package com.example.oop_project_part2_modified;

import java.io.Serializable;
import java.util.ArrayList;

public class dataBase implements Serializable {
    private foodList fl;
    private restaurantList rl;

    public dataBase(){
        fl=new foodList();
        rl=new restaurantList();
    }

    public int getFoodNum(){
        return fl.getFLSize();
    }

    public int getRestaurantNum(){
        return rl.getRLSize();
    }

    public void addFood(food f) {
        fl.addFood(f);
    }

    public restaurant getRestaurant(int i) {
        return rl.getRestaurant(i);
    }

    public food getFood(int i) {
        return fl.getFood(i);
    }

    public void addRestaurant(restaurant r) {
        rl.addRestaurant(r);
    }
    public ArrayList<Integer> searchRestaurantById(int Id){ return rl.searchByID(Id); }

    public ArrayList<Integer> searchRestaurantByName(String Name) {
        return rl.searchByName(Name);
    }

    public ArrayList<Integer> searchRestaurantByScore(double low, double high) {
        return rl.searchByScore(low, high);
    }

    public ArrayList<Integer> searchRestaurantByCategory(String Category) {
        return rl.searchByCategory(Category);
    }

    public ArrayList<Integer> searchRestaurantByPrice(String Price) {
        return rl.searchByPrice(Price);
    }

    public ArrayList<Integer> searchRestaurantByZipCode(String zipCode) {
        return rl.searchByZipCode(zipCode);
    }

    public ArrayList<Integer> searchFoodByName(String fName){
        return fl.searchByName(fName);
    }

    public ArrayList<Integer> searchByNameInRestaurant(String fName, String rName) {
        int r;
        ArrayList<Integer> fm = fl.searchByName(fName);
        if( rl.searchByName(rName).size()!=0 && fm.size()!=0){
            r=rl.searchByName(rName).get(0);
            ArrayList<Integer> nameUnderRestaurant = new ArrayList<>();
            for (int i = 0; i < fm.size(); i++) {
                if (rl.getRestaurant(r).getId() == fl.getFood(fm.get(i)).getId()) {
                    nameUnderRestaurant.add(fm.get(i));
                }
            }
            return nameUnderRestaurant;
        }
        else
            return new ArrayList<Integer>();
    }

    public ArrayList<Integer> searchFoodByCategory(String Category) {
        return fl.searchByCategory(Category);
    }

    public ArrayList<Integer> searchFoodByCategoryUnderRestaurant(String fCategory, String rName) {
        int r ;
        ArrayList<Integer> fm = fl.searchByCategory(fCategory);
        if(rl.searchByName(rName).size()!=0 && fm.size()!=0){
            r=rl.searchByName(rName).get(0);
            ArrayList<Integer> categoryUnderRestaurant = new ArrayList<>();
            for (int i = 0; i < fm.size(); i++) {
                if (rl.getRestaurant(r).getId() == fl.getFood(fm.get(i)).getId()) {
                    categoryUnderRestaurant.add(fm.get(i));
                }
            }
            return categoryUnderRestaurant;
        }
        return new ArrayList<Integer>();
    }

    public ArrayList<Integer> searchFoodBetRange(double low, double high){
        return fl.searchByRange(low,high);
    }

    public ArrayList<Integer> searchFoodBetRangeUnderRestaurant(double low, double high, String rName){
        int r;
        ArrayList<Integer> fBetRange=fl.searchByRange(low,high);
        if(rl.searchByName(rName).size()!=0 && fBetRange.size()!=0){
            r=rl.searchByName(rName).get(0);
            ArrayList<Integer> fBetRangeunderR=new ArrayList<>();
            for(int i=0;i<fBetRange.size();i++){
                if(rl.getRestaurant(r).getId()==fl.getFood(fBetRange.get(i)).getId()){
                    fBetRangeunderR.add(fBetRange.get(i));
                }
            }
            return fBetRangeunderR;
        }
            return new ArrayList<Integer>();
    }

    public ArrayList<Integer> searchCostliestFoodUnderRestaurant(String rName) {
        int r ;
        if( rl.searchByName(rName).size()!=0){
            r=rl.searchByName(rName).get(0);
            ArrayList<Integer> fm = fl.underRestaurant(rl.getRestaurant(r).getId());
            double maxPrice=fl.getFood(fm.get(0)).getPrice();
            ArrayList<Integer> costliestFoodUnderRestaurant = new ArrayList<>();
            for (int i = 0; i < fm.size(); i++) {
                if (fl.getFood(fm.get(i)).getPrice()>maxPrice) {
                    maxPrice=fl.getFood(fm.get(i)).getPrice();
                }
            }
            for(int i=0;i<fm.size();i++){
                if(maxPrice==fl.getFood(fm.get(i)).getPrice()){
                    costliestFoodUnderRestaurant.add(fm.get(i));
                }
            }
            return costliestFoodUnderRestaurant;
        }
        return new ArrayList<Integer>();
    }

    public ArrayList<Integer> searchFoodUnderRestaurant(int restId){
        return fl.underRestaurant(restId);
    }
}
