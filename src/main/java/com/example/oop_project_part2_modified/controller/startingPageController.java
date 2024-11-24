package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.client.client;
import com.example.oop_project_part2_modified.client.clientRestaurant;
import com.example.oop_project_part2_modified.restaurant;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class startingPageController {

    @FXML
    private AnchorPane clientRestaurant;
    @FXML
    private Button orderButton;
    private Image coffee;
    @FXML
    private ImageView logoCoffee;
    @FXML
    private Button restaurantManager;
    private ArrayList<searchAndOrderController> sAndOController=new ArrayList<>();
    private HashMap<restaurant,ArrayList<clientRestaurantPage>> restaurantWindows;
    private ArrayList<restaurantLoginController> restaurantLoginControllers=new ArrayList<>();

    public void setImageView(Image image){
            logoCoffee.setImage(image);
    }

    public void startLoginPage(MouseEvent event){
        Node node=(Node)event.getSource();
        Stage parentStage=(Stage)node.getScene().getWindow();
        restaurantLoginController rLoginController= new restaurantLoginController(restaurantWindows);
        restaurantLoginControllers.add(rLoginController);
    }

    public void setRestaurantWindows(HashMap<restaurant,ArrayList<clientRestaurantPage>> restaurantWindows) { this.restaurantWindows=restaurantWindows; }

    //starts order window for client by clicking order button
    @FXML
    void searchAndOrder(MouseEvent event) {
        client customer=new client();
        searchAndOrderController temp=new searchAndOrderController(customer);
        sAndOController.add(temp);

    }
}







