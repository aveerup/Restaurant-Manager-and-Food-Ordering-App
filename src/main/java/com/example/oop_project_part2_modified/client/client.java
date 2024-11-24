package com.example.oop_project_part2_modified.client;

import com.example.oop_project_part2_modified.dataBase;
import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.restaurant;
import javafx.scene.control.Button;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class client{

    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private dataBase d;
    private HashMap<food,Integer> order;
    private ArrayList<restaurant> loggedInRestaurant=new ArrayList<>();
    private Integer clientNo;
    private static int clientCount=0;

    public client(){
        try {
            System.out.println("client running "+clientCount);
            socket=new Socket("localhost",22222);
            oos=new ObjectOutputStream(socket.getOutputStream());
            ois=new ObjectInputStream(socket.getInputStream());
            oos.writeObject("client");
            System.out.println("client "+1.1);
            this.clientNo=(Integer) ois.readObject();
            this.loggedInRestaurant=(ArrayList<restaurant>) ois.readObject();
            d=(dataBase) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getClientNo() { return clientNo; }
    public dataBase getDataBase(){
        return this.d;
    }

    public ArrayList<restaurant> getLoggedInRestaurant(){
        return loggedInRestaurant;
    }

    public void setOrder(HashMap<food,Integer> order) { this.order=order; }

    public HashMap<food,Integer> getOrder(){ return order; }

    public void sendOrderToServer(){
        try {
            oos.writeObject(order);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
