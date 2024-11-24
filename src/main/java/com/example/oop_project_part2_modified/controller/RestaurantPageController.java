package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.client.client;
import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class RestaurantPageController {

    private Stage parentStage=new Stage();

    private client customer=new client();

    private restaurant r;

    @FXML
    private Button baxkButton;

    @FXML
    private AnchorPane clientRestaurantPageAnchor;

    @FXML
    private TableColumn<food, String> fCategory;

    @FXML
    private TableColumn<food,String> fName;

    @FXML
    private TableColumn<food, Double> fPrice;

    @FXML
    private TableView<food> restaurantMenu;

    @FXML
    private Label rIDLabel=new Label();

    @FXML
    private Label rNameLabel=new Label();

    @FXML
    private Label rRatingLabel=new Label();

    @FXML
    private Label rZipCodeLabel=new Label();

    private ArrayList<food> rFoodList;

    public ObservableList<food> observableList= FXCollections.observableArrayList();

    public void setClient(client customer){ this.customer=customer; }
    public void setParentStage(Stage parentStage){
        this.parentStage = parentStage;
    }

    @FXML
    public void backButtonClicked(MouseEvent event) {
        Node node=(Node)event.getSource();
        Stage stage=(Stage) node.getScene().getWindow();
        stage.hide();
        parentStage.show();
    }

    public RestaurantPageController(restaurant r){
        this.r=r;
        setRFoodList();
        setRestaurantPage();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/RestauratnPage.fxml"));
        Parent root= null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(root,600,700);
        stage.setScene(scene);
        stage.show();
    }

    public void setRFoodList(){
        //this.r=customer.getDataBase().getRestaurant(customer.getDataBase().searchRestaurantByName(selectedRestaurantName).get(0));
        this.rFoodList=r.getMenuArrayList();
    }

    public void setRestaurantPage(){
        //setting up restaurant info
        rIDLabel.setText(Integer.toString(r.getId()));
        rNameLabel.setText(r.getName());
        rZipCodeLabel.setText(r.getzipCode());
        rRatingLabel.setText(Double.toString(r.getScore()));


        //setting up table with restaurant menu
        fCategory.setCellValueFactory(new PropertyValueFactory<food,String>("category"));
        fName.setCellValueFactory(new PropertyValueFactory<food,String>("name"));
        fPrice.setCellValueFactory(new PropertyValueFactory<food,Double>("price"));
        for(food f:rFoodList) observableList.add(f);
        restaurantMenu.setItems(observableList);
    }

    @FXML
    void print(MouseEvent event) {

    }

}

