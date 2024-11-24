package com.example.oop_project_part2_modified.server;

import com.example.oop_project_part2_modified.controller.RestaurantPageController;
import com.example.oop_project_part2_modified.controller.clientRestaurantPage;
import com.example.oop_project_part2_modified.dataBase;
import com.example.oop_project_part2_modified.restaurant;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class ServeRestaurant implements Runnable {
    private Socket socket;
    private dataBase d;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<restaurant> loggedInRestaurant;
    private ArrayList<clientRestaurantPage> rController;
    private HashMap<String,ObjectOutputStream> rPathway;
    private static int serveRestaurantCount=0;

    public ServeRestaurant(Socket socket, dataBase d, ObjectOutputStream oos, ObjectInputStream ois, ArrayList<restaurant> loggedInRestaurant, ArrayList<clientRestaurantPage> rController, HashMap<String,ObjectOutputStream> rPathway){
            this.d=d;
            this.socket=socket;
            this.oos=oos;
            this.ois=ois;
            this.loggedInRestaurant=loggedInRestaurant;
            this.rController=rController;
            this.rPathway=rPathway;
            serveRestaurantCount++;
            Thread t=new Thread(this);
            t.start();
    }

    @Override
    public void run(){
        try {
            System.out.println("serve restaurant client running "+serveRestaurantCount);
            System.out.println("restaurant client is running ");
            String restaurantName;
            System.out.println("serveRestaurant "+1.1);
            restaurantName=(String) ois.readObject();
            rPathway.put(restaurantName.toUpperCase(),oos);
            restaurant r=d.getRestaurant(d.searchRestaurantByName(restaurantName).get(0));
            boolean match=false;
            for(restaurant r1:loggedInRestaurant){
                if(r.getId()==r1.getId()) match=true;
            }
            if(!match)
                loggedInRestaurant.add(r);
            this.oos.writeObject(r);
            while(true){
                ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
