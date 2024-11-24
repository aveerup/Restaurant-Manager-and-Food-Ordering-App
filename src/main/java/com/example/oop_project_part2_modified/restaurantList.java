package com.example.oop_project_part2_modified;

import java.io.Serializable;
import java.util.ArrayList;

public class restaurantList implements Serializable {
   private ArrayList<restaurant> restaurantlist;

   public restaurantList(){
      restaurantlist=new ArrayList<>();
   }

   public restaurant getRestaurant(int i){
      return restaurantlist.get(i);
   }

   public void addRestaurant(restaurant r){
      restaurantlist.add(r);
   }

   public int getRLSize(){
      return restaurantlist.size();
   }

   public ArrayList<Integer> searchByName(String Name){
      ArrayList<Integer> rByName=new ArrayList<>();
      for(int i=0;i<restaurantlist.size();i++){
         if(restaurantlist.get(i).getName().toUpperCase().contains(Name.toUpperCase())){
            rByName.add(i);
         }
      }
      return rByName;
   }

   public ArrayList<Integer> searchByScore(double low, double high){
      ArrayList<Integer> betScore=new ArrayList<>();
      for(int i=0;i<restaurantlist.size();i++){
         if(restaurantlist.get(i).getScore()>=low && restaurantlist.get(i).getScore()<=high){
            betScore.add(i);
         }
      }
      return betScore;
   }

   public ArrayList<Integer> searchByCategory(String Category){
      ArrayList<Integer> cat=new ArrayList<>();
      for(int i=0;i<restaurantlist.size();i++){
            for(int j=0;j<3;j++){
               if(restaurantlist.get(i).getCategories()[j].toUpperCase().contains(Category.toUpperCase())) {
                  cat.add(i);
                  break;
               }
           }
      }
      return cat;
   }

   public ArrayList<Integer> searchByPrice(String Price){
      ArrayList<Integer> underPrice=new ArrayList<>();
//      String p=new String();
//      if(Price>=1 && Price<=10) p="$";
//      else if(Price>=11 && Price<=20) p="$$";
//      else if(Price>=21 && Price<=30) p="$$$";
//      else return underPrice;

      for(int i=0;i<restaurantlist.size();i++){
         if(restaurantlist.get(i).getPrice().equals(Price)){
            underPrice.add(i);
         }
      }
      return underPrice;
   }

   public ArrayList<Integer> searchByZipCode(String zipCode){
      ArrayList<Integer> underZipCode=new ArrayList<>();
      for(int i=0;i<restaurantlist.size();i++){
         if(restaurantlist.get(i).getzipCode().equals(zipCode)){
            underZipCode.add(i);
         }
      }
      return underZipCode;
   }

   public ArrayList<Integer> searchByID(int Id){
      ArrayList<Integer> underId=new ArrayList<>();
      for(int i=0;i<restaurantlist.size();i++){
         if(restaurantlist.get(i).getId()==Id){
            underId.add(i);
         }
      }
      return underId;
   }

}
