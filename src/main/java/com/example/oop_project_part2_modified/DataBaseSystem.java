package com.example.oop_project_part2_modified;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseSystem {

    public static void main(String[] args){
        dataBase d1=new dataBase();
        boolean change=false;
        Scanner scanner;
        try{
            File file1=new File("D:\\Java\\OOP_project1\\src\\menu.txt");
            File file2=new File("D:\\Java\\OOP_project1\\src\\restaurant.txt");

            scanner=new Scanner(file1);
            while(scanner.hasNextLine()){
                String menu=scanner.nextLine();
                String[] parts=menu.split("[,]");
                int Id=Integer.parseInt(parts[0]);
                String Category=parts[1];
                String Name=parts[2];
                double Price=Double.parseDouble(parts[3]);
                food f=new food(Id,Category,Name,Price);
                d1.addFood(f);
            }
            scanner.close();

            scanner=new Scanner(file2);
            while(scanner.hasNextLine()){
                String res=scanner.nextLine();
                String[] parts=res.split("[,]");
                int Id=Integer.parseInt(parts[0]);
                String Name=parts[1];
                //System.out.println(parts.length);
                double Score=Double.parseDouble(parts[2]);
                String Price=parts[3];
                String zipCode=parts[4];
                String[] Categories=new String[3];
                if(parts.length==8){
                    Categories[0]=parts[5];
                    Categories[1]=parts[6];
                    Categories[2]=parts[7];
                }
                else {
                    Categories[0]=parts[5];
                    Categories[1]=parts[6];
                    Categories[2]="";
                }
                ArrayList<Integer> menuIndex=d1.searchFoodUnderRestaurant(Id);
                foodList menu=new foodList();
                for(int i=0;i<menuIndex.size();i++){
                    menu.addFood(d1.getFood(menuIndex.get(i)));
                }
                restaurant r=new restaurant(Id, Name, Score,Price, zipCode,Categories,menu);
                d1.addRestaurant(r);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(true){
            System.out.println("Main menu: ");
            System.out.println("1) Search Restaurants");
            System.out.println("2) Search Food Items");
            System.out.println("3) Add Restaurant");
            System.out.println("4) Add Food Item to the Menu");
            System.out.println("5) Exit System");
            System.out.println("Choose an option:");
            scanner=new Scanner(System.in);
            int choice=scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1:
                    boolean run1=true;
                    while(run1){
                        System.out.println("Restaurant Searching Options: ");
                        System.out.println("1) By Name");
                        System.out.println("2) By Score ");
                        System.out.println("3) By Category ");
                        System.out.println("4) By Price ");
                        System.out.println("5) By Zip Code ");
                        System.out.println("6) Different Category Wise List of Restaurants ");
                        System.out.println("7) Back to Main Menu ");

                        choice=scanner.nextInt();
                        scanner.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("Enter a restaurant name:");
                                ArrayList<Integer> rByName=d1.searchRestaurantByName(scanner.nextLine());
                                if(rByName.size()==0)
                                    System.out.println("No such restaurant with this name ");
                                else
                                {
                                    for(int i:rByName)
                                    d1.getRestaurant(i).showDetails();
                                }
                                break;
                            case 2:
                                System.out.print("Enter two scores: ");
                                ArrayList<Integer> rByScore= d1.searchRestaurantByScore(scanner.nextDouble(),scanner.nextDouble());
                                if(rByScore.size()==0)
                                    System.out.println("No restaurants in this score range ");
                                else
                                {
                                    for(int i=0;i<rByScore.size();i++){
                                        d1.getRestaurant(rByScore.get(i)).showDetails();
                                    }
                                }
                                break;
                            case 3:
                                System.out.print("Enter category of restaurant: ");
                                ArrayList<Integer> rByCategory=d1.searchRestaurantByCategory(scanner.nextLine());
                                if(rByCategory.size()==0)
                                    System.out.println("No restaurants in this Category ");
                                else
                                {
                                    for(int i=0;i<rByCategory.size();i++){
                                        d1.getRestaurant(rByCategory.get(i)).showDetails();
                                    }
                                }
                                break;
                            case 4:
                                System.out.println("Input price($/$$/$$):");
                                String rPrice=scanner.nextLine();
                                ArrayList<Integer> rByPrice=d1.searchRestaurantByPrice(rPrice);
                                if(rPrice.equals("$")|rPrice.equals("$$")|rPrice.equals("$$$")){
                                    for(int i=0;i<rByPrice.size();i++){
                                        d1.getRestaurant(rByPrice.get(i)).showDetails();
                                    }
                                }
                                else
                                    System.out.println("Input price is not valid.");
                                break;
                            case 5:
                                ArrayList<Integer> rByZipCode=d1.searchRestaurantByZipCode(scanner.nextLine());
                                if(rByZipCode.size()==0)
                                    System.out.println("No restaurants in this zipcode");
                                else
                                {
                                    for(int i=0;i<rByZipCode.size();i++){
                                        d1.getRestaurant(rByZipCode.get(i)).showDetails();
                                    }
                                }
                                break;
                            case 6:
                                ArrayList<String> Category=new ArrayList<>();
                                for(int i=0;i<d1.getRestaurantNum();i++){
                                    for(int j=0;j<3;j++){
                                        boolean match=false;
                                        for(int k=0;k< Category.size();k++){
                                            if(d1.getRestaurant(i).getCategories()[j].equals(Category.get(k)))
                                                match=true;
                                        }
                                        if(!match)
                                            Category.add(d1.getRestaurant(i).getCategories()[j]);
                                    }
                                }
                                for(int i=0;i< Category.size();i++){
                                    ArrayList<Integer> tempRByCategory=d1.searchRestaurantByCategory(Category.get(i));
                                    if(Category.get(i).equals("")) continue;
                                    System.out.print(Category.get(i)+": ");
                                    for(int j=0;j<tempRByCategory.size();j++){
                                        System.out.print(d1.getRestaurant(tempRByCategory.get(j)).getName());
                                        if(j!=tempRByCategory.size()-1)
                                            System.out.print(",");
                                    }
                                    System.out.println("");
                                }
                                break;
                            case 7:
                                run1=false;
                                break;
                            default:
                                System.out.println("Not a valid option. Try again.");
                        }
                    }
                    break;
                case 2:
                    boolean run2=true;
                    while(run2){
                        System.out.println("Food Item Searching Options:");
                        System.out.println("1) By Name");
                        System.out.println("2) By Name in a Given Restaurant");
                        System.out.println("3) By Category");
                        System.out.println("4) By Category in a Given Restaurant");
                        System.out.println("5) By Price Range");
                        System.out.println("6) By Price Range in a Given Restaurant");
                        System.out.println("7) Costliest Food Item(s) on the Menu in a Given Restaurant");
                        System.out.println("8) List of Restaurants and Total Food Item on the Menu");
                        System.out.println("9) Back to Main Menu");
                        System.out.println("Choose option: ");

                        choice =scanner.nextInt();
                        scanner.nextLine();
                        switch(choice){
                            case 1:
                                System.out.print("Enter food name: ");
                                ArrayList<Integer> fByName= d1.searchFoodByName(scanner.nextLine());
                                for(int i=0;i< fByName.size();i++){
                                    if(fByName.size()==0){
                                        System.out.println("No food with such name ");
                                        break;
                                    }
                                    d1.getFood(fByName.get(i)).showDetails();
                                }
                                break;
                            case 2:
                                System.out.print("Enter your desired food and restaurant name : ");
                                ArrayList<Integer> fByNameInR=d1.searchByNameInRestaurant(scanner.nextLine(),scanner.nextLine());
                                for(int i=0;i< fByNameInR.size();i++){
                                    if(fByNameInR.size()==0){
                                        System.out.println("No food with such name in the restaurant ");
                                        break;
                                    }
                                    d1.getFood(fByNameInR.get(i)).showDetails();
                                }
                                break;
                            case 3:
                                System.out.print("Enter your desired category: ");
                                ArrayList<Integer> fByCategory=d1.searchFoodByCategory(scanner.nextLine());
                                for(int i=0;i<fByCategory.size();i++){
                                    if(fByCategory.size()==0){
                                        System.out.println("No food with such category ");
                                        break;
                                    }
                                    d1.getFood(fByCategory.get(i)).showDetails();
                                }
                                break;
                            case 4:
                                System.out.print("Enter your desired category and desired restaurant :");
                                ArrayList<Integer> fByCategoryUnderR=d1.searchFoodByCategoryUnderRestaurant(scanner.nextLine(),scanner.nextLine());
                                for(int i=0;i<fByCategoryUnderR.size();i++){
                                    if(fByCategoryUnderR.size()==0){
                                        System.out.println("No food with such category in the restaurant ");
                                        break;
                                    }
                                    d1.getFood(fByCategoryUnderR.get(i)).showDetails();
                                }
                                break;
                            case 5:
                                System.out.print("Enter two price range: ");
                                ArrayList<Integer> fByPriceRange=d1.searchFoodBetRange(scanner.nextDouble(),scanner.nextDouble());
                                for(int i=0;i<fByPriceRange.size();i++){
                                    if(fByPriceRange.size()==0){
                                        System.out.println("No food with such name in such price range ");
                                        break;
                                    }
                                    d1.getFood(fByPriceRange.get(i)).showDetails();
                                }
                                break;
                            case 6:
                                System.out.print("Enter two ranges : ");
                                double low=scanner.nextDouble();
                                double high=scanner.nextDouble();
                                scanner.nextLine();
                                System.out.print("Enter restaurant name: ");
                                ArrayList<Integer> fBetRangeUnderR=d1.searchFoodBetRangeUnderRestaurant(low, high,scanner.nextLine());
                                for(int i=0;i<fBetRangeUnderR.size();i++){
                                    if(fBetRangeUnderR.size()==0){
                                        System.out.println("No food with such name in the price range in the restaurant ");
                                        break;
                                    }
                                    d1.getFood(fBetRangeUnderR.get(i)).showDetails();
                                }
                                break;
                            case 7:
                                System.out.print("Enter your desired restaurant name: ");
                                ArrayList<Integer> fCostliestUnderR=d1.searchCostliestFoodUnderRestaurant(scanner.nextLine());
                                for(int i=0;i<fCostliestUnderR.size();i++){
                                    d1.getFood(fCostliestUnderR.get(i)).showDetails();
                                }
                                break;
                            case 8:
                                //System.out.println(d1.getFoodNum()+" "+d1.getRestaurantNum());
                                if(d1.getRestaurantNum()==0){
                                    System.out.println("No restaurant available ");
                                }
                                for(int i=0;i<d1.getRestaurantNum();i++){
                                    System.out.print(d1.getRestaurant(i).getName()+": ");
                                    System.out.println(d1.searchFoodBetRangeUnderRestaurant(0,200,d1.getRestaurant(i).getName()).size());
                                }
                                break;
                            case 9:
                                run2=false;
                                break;
                            default:
                                System.out.println("Not a valid option.Try again.");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Fill out the following info about the restaurant: ");
                    System.out.print("Id:");
                    int rId=scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name:");
                    String rName=scanner.nextLine();
                    System.out.print("Score(out of 5):");
                    double rScore;
                    while(true){
                        rScore=scanner.nextDouble();
                        scanner.nextLine();
                        if(rScore<=5) break; else System.out.println("Invalid Score ");
                    }
                    System.out.print("Price($/$$/$$$):");
                    String rPrice;
                    while(true){
                        rPrice=scanner.nextLine();
                        if(rPrice.equals("$")||rPrice.equals("$$")||rPrice.equals("$$$")) break; else System.out.println("Invalid Price ");
                    }
                    System.out.print("Zipcode: ");
                    String rZipCode=scanner.nextLine();
                    System.out.println("Input categories (maximum 3): ");
                    String[] rCategories=new String[3];
                    rCategories[0]=scanner.nextLine();
                    rCategories[1]=scanner.nextLine();
                    rCategories[2]=scanner.nextLine();
                    restaurant rTemp=new restaurant(rId,rName,rScore,rPrice,rZipCode,rCategories);
                    if(d1.searchRestaurantByName(rName).size()==0){
                        d1.addRestaurant(rTemp);
                        change=true;
                    }
                    break;
                case 4:
                    System.out.print("Input the restaurant name you desire to add food item in: ");
                    String resName=scanner.nextLine();
//                    int rIndex=d1.searchRestaurantByName(resName);
                    while(true) {
                        if (d1.searchRestaurantByName(resName).size()!=0) {
                            int fId = d1.getRestaurant(d1.searchRestaurantByName(resName).get(0)).getId();
                            System.out.print("Input name of food:");
                            String Name = scanner.nextLine();
                            System.out.print("Input category of food:");
                            String Category = scanner.nextLine();
                            System.out.print("Input price of food: ");
                            double Price = scanner.nextDouble();
                            scanner.nextLine();
                            food f = new food(fId, Category, Name, Price);
                            d1.addFood(f);
                            change=true;
                            break;
                        }
                        else
                            System.out.println("No such restaurant exists. Try again. ");
                    }
                    break;
                case 5:
                    if(change){
                    try {
                        File file1=new File("D:\\Java\\OOP_project1\\src\\menu.txt");
                        File file2=new File("D:\\Java\\OOP_project1\\src\\restaurant.txt");
                        PrintWriter out=new PrintWriter(file1);
                        for(int i=0;i<d1.getFoodNum();i++){
                            out.println(d1.getFood(i).getId()+","+d1.getFood(i).getCategory()+","+d1.getFood(i).getName()+","+d1.getFood(i).getPrice());
                        }
                        out.close();

                        out=new PrintWriter(file2);
                        for(int i=0;i<d1.getRestaurantNum();i++){
                            out.println(d1.getRestaurant(i).getId()+","+d1.getRestaurant(i).getName()+","+d1.getRestaurant(i).getScore()+","+d1.getRestaurant(i).getPrice()+","+d1.getRestaurant(i).getzipCode()+","+d1.getRestaurant(i).getCategories()[0]+","+d1.getRestaurant(i).getCategories()[1]+","+d1.getRestaurant(i).getCategories()[2]);
                        }
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                      }
                    }
                    return;
            }
        }
    }
}
