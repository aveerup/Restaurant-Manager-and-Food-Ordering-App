package com.example.oop_project_part2_modified.server;

import com.example.oop_project_part2_modified.dataBase;
import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.restaurant;
import javafx.fxml.FXML;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class serveClient implements Runnable {
    private Socket socket;
    private dataBase d;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private ArrayList<restaurant> loggedInRestaurant;
    private HashMap<food,Integer> order;
    private HashMap<String,ObjectOutputStream> rPathway;
    private Integer clientNo;
    private static int serveClientCount=0;

    public serveClient(Socket socket, dataBase d, ObjectOutputStream oos, ObjectInputStream ois, ArrayList<restaurant> loggedInRestaurant, HashMap<String,ObjectOutputStream> rPathway, HashMap<food,Integer> order,Integer clientNo){
        this.socket=socket;
        this.d=d;
        this.oos=oos;
        this.ois=ois;
        this.loggedInRestaurant=loggedInRestaurant;
        this.rPathway=rPathway;
        this.order=order;
        this.clientNo=clientNo;
        serveClientCount++;
        Thread t=new Thread(this);
        t.start();
    }

    @Override
    public void run(){
        try {
            System.out.println("Running serve client "+serveClientCount);
            oos.writeObject(clientNo);
            oos.writeObject(loggedInRestaurant);
            oos.writeObject(d);
            while(true){
                order=(HashMap<food, Integer>) ois.readObject();
                sortOrder(order);
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    synchronized public void sortOrder(HashMap<String,Integer> order){
        try {
            for(Map.Entry e:order.entrySet()){
                Pair<Pair<Integer,Integer>,food> pack=new Pair<>(new Pair<>(clientNo,(Integer)e.getValue()),d.getFood(d.searchFoodByName((String)e.getKey()).get(0)));

                rPathway.get(pack.getValue().getRestaurantName().toUpperCase()).writeObject(pack);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
