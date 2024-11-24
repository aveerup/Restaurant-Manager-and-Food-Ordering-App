package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.client.clientRestaurant;
import com.example.oop_project_part2_modified.restaurant;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class restaurantLoginController {
    private showAlertController alert=new showAlertController();
    @FXML
    private Button loginButton;
    @FXML
    private TextField loginRName=new TextField();
    @FXML
    private TextField rLoginPassword=new TextField();
    private HashMap<String,String> rNamePassword=new HashMap<>();
    private Stage parentStage;
    private HashMap<restaurant,ArrayList<clientRestaurantPage>> restaurantWindows;
    private HashMap<restaurant, Pair<clientRestaurantPage,Scene>> loggedInRestaurant=new HashMap<>();
    private boolean stageShown=false;

    public void setParentStage(Stage parentStage) {
        this.parentStage = parentStage;
    }

    public restaurantLoginController(HashMap<restaurant,ArrayList<clientRestaurantPage>> restaurantWindows){
        this.restaurantWindows=restaurantWindows;
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/RestaurantLoginPage.fxml"));
        try {
            Parent root=fxmlLoader.load();
            Scene scene=new Scene(root,694,404);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File file=new File("src\\main\\java\\com\\example\\oop_project_part2_modified\\TextFiles\\RestaurantAndPassword.txt");
        try {
            Scanner scanner=new Scanner(file);
            String temp;
            while(scanner.hasNextLine()) {
                temp=scanner.nextLine();
                String[] parts=temp.split(",");
                System.out.println(parts[0]+" "+parts[1]);
                rNamePassword.put(parts[0],parts[1]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void loginButtonClicked(MouseEvent event) {
        String name=loginRName.getText();
        System.out.println("loginRName "+name);
        String pass=rLoginPassword.getText();
        if(name.equals("") && !pass.equals("")) alert.showAlert("Please Enter Restaurant Name");
        else if(!name.equals("") && pass.equals("")) alert.showAlert("Please Enter Password");
        else if(name.equals("") && pass.equals("")) alert.showAlert("Fill Up The Fields Please");
        else{
            System.out.println("rLoginPassword " + pass);
            try {
                for(restaurant r:loggedInRestaurant.keySet())
                    if(r.getName().toUpperCase().equals(name.toUpperCase())){
                        System.out.println("restaurantLoginController: Already included restaurant ");
                        Stage stage=new Stage();
//                        FXMLLoader fxmlLoader =new FXMLLoader();
//                        System.out.println("restaurantLoginController: "+1.1);
//                        fxmlLoader.setController(loggedInRestaurant.get(r));
//                        System.out.println("restaurantLoginController: "+1.2);
//                        Scene scene=new Scene(fxmlLoader.load(),685,935);
                        System.out.println("restaurantLoginController: "+1.3);
                        stage.setScene(loggedInRestaurant.get(r).getValue());
                        stage.show();
                        stageShown=true;
                        break;
                    }
                if(!stageShown) {
                    if (rNamePassword.get(name.toUpperCase()).equals(pass)) {
                        new clientRestaurant(name, loggedInRestaurant);
                    } else alert.showAlert("Wrong Info, Try Again ");
                }
                stageShown=false;
            }catch (RuntimeException e){
                //alert.showAlert("Wrong Info,Try Again ");
                throw new RuntimeException();
            }


        }
    }

}
