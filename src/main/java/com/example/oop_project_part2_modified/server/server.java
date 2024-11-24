package com.example.oop_project_part2_modified.server;

import com.example.oop_project_part2_modified.controller.clientRestaurantPage;
import com.example.oop_project_part2_modified.dataBase;
import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.foodList;
import com.example.oop_project_part2_modified.restaurant;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class server {
    private dataBase d1;

    private ServerSocket serverSocket;

    private ArrayList<restaurant> loggedInRestaurant=new ArrayList<>();

    private ArrayList<clientRestaurantPage> rController=new ArrayList<>();

    private HashMap<String, ObjectOutputStream> rPathway=new HashMap<>();

    private HashMap<String,Integer> order=new HashMap<>();

    private Integer clientNo=0;

    private Integer restauratnNO=0;

    private Socket socket;
    public server(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
            d1 = new dataBase();
            Scanner scanner;

            File file1 = new File("src\\main\\java\\com\\example\\oop_project_part2_modified\\menu.txt");
            File file2 = new File("src\\main\\java\\com\\example\\oop_project_part2_modified\\restaurant.txt");

            scanner = new Scanner(file1);
            while (scanner.hasNextLine()) {
                String menu = scanner.nextLine();
                String[] parts = menu.split("[,]");
                int Id = Integer.parseInt(parts[0]);
                String Category = parts[1];
                String Name = parts[2];
                double Price = Double.parseDouble(parts[3]);
                food f = new food(Id, Category, Name, Price);
                d1.addFood(f);
            }
            scanner.close();

            scanner = new Scanner(file2);
            while (scanner.hasNextLine()) {
                String res = scanner.nextLine();
                String[] parts = res.split("[,]");
                int Id = Integer.parseInt(parts[0]);
                String Name = parts[1];
                //System.out.println(parts.length);
                double Score = Double.parseDouble(parts[2]);
                String Price = parts[3];
                String zipCode = parts[4];
                String[] Categories = new String[3];
                if (parts.length == 8) {
                    Categories[0] = parts[5];
                    Categories[1] = parts[6];
                    Categories[2] = parts[7];
                } else {
                    Categories[0] = parts[5];
                    Categories[1] = parts[6];
                    Categories[2] = "";
                }
                //String[] Categories={parts[5],parts[6],parts[7]};
                ArrayList<Integer> menuIndex = d1.searchFoodUnderRestaurant(Id);
                foodList menu = new foodList();
                for (int i = 0; i < menuIndex.size(); i++) {
                    menu.addFood(d1.getFood(menuIndex.get(i)));
                }
                restaurant r = new restaurant(Id, Name, Score, Price, zipCode, Categories, menu);
                d1.addRestaurant(r);
            }
            scanner.close();
            for(int i=0;i<d1.getFoodNum();i++){
                d1.getFood(i).setRestaurantName(d1.getRestaurant(d1.searchRestaurantById(d1.getFood(i).getId()).get(0)).getName());
            }
            while (true) {
                socket = serverSocket.accept();
                System.out.println("client connected ...");
                new serve(socket,d1,loggedInRestaurant,rController,rPathway,order,clientNo,restauratnNO);
            }
        } catch (IOException e) {
        throw new RuntimeException(e);
        }
    }

        public static void main(String[] args) {
        new server(22222);
    }

}

