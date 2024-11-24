package com.example.oop_project_part2_modified.client;

import com.example.oop_project_part2_modified.controller.RestaurantPageController;
import com.example.oop_project_part2_modified.controller.clientRestaurantPage;
import com.example.oop_project_part2_modified.controller.startingPageController;
import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.restaurant;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.module.ResolutionException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class clientRestaurant implements Runnable {

    private String rName;
    private restaurant r;
    private Socket socket;
    private clientRestaurantPage crPageController;
    private ArrayList<Pair<Pair<Integer,Integer>,food>> orders=new ArrayList<Pair<Pair<Integer,Integer>,food>>();
    private ArrayList<String> orderString=new ArrayList<>();
    private static int clientRestaurantCount=0;
    private HashMap<restaurant,Pair<clientRestaurantPage,Scene>> loggedInRestaurant;

    public clientRestaurant(String rName, HashMap<restaurant,Pair<clientRestaurantPage,Scene>> loggedInRestaurant){
            this.rName=rName;
            this.loggedInRestaurant=loggedInRestaurant;
            Thread t=new Thread(this);
            t.start();
    }

    @Override
    public void run() {

        try {
            System.out.println("client Restaurant running "+clientRestaurantCount);
            this.socket = new Socket("localhost", 22222);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            oos.writeObject("restaurant");
            System.out.println("clientRestaurant " + 1.3);
            //rID = scanner.nextInt();
            //scanner.nextLine();

            oos.writeObject(rName);
            System.out.println("clientRestaurant " + 1.4);
            this.r = (restaurant) ois.readObject();
            crPageController=new clientRestaurantPage(r);
            //this.orders=(ArrayList<Pair<Pair<Integer, Integer>, food>>) ois.readObject();

            //new platform.
//            Platform.runLater(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//
//                            try {
//                                Stage stage=new Stage();
//                                FXMLLoader fxmlLoader=new FXMLLoader(startingPageController.class.getResource("/com/example/oop_project_part2/clientRestaurantPage.fxml"));
//                                Parent root=fxmlLoader.load();
//                                clientRestaurantPage controller=fxmlLoader.getController();
//                                crPageController=controller;
//                                for(food f:r.getMenuArrayList()) controller.observableList.add(f);
//                                System.out.println("Is the list empty? " +controller.observableList.isEmpty());
//                                controller.clientRestaurantPageUpdate(r);
//                                Scene scene= null;
//                                scene = new Scene(root,935,685);
//                                loggedInRestaurant.put(r,new Pair<>(controller,scene));
//                                stage.setScene(scene);
//                                stage.show();
//
//                            } catch (IOException e) {
//                                throw new RuntimeException(e);
//                            }
//                        }
//                    }
//            );
            while(true){
                Pair<Pair<Integer,Integer>,food> temp=(Pair<Pair<Integer,Integer>,food>)ois.readObject();
                orders.add(temp);
                String s="client "+temp.getKey().getKey()+" ordered "+temp.getKey().getValue()+" "+temp.getValue().getName()+" ";
                crPageController.updateOrderList(s);
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}