package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

public class clientRestaurantPage implements Initializable, Serializable {

    @FXML
    private TableColumn<food, String> fCategory;
    @FXML
    private TableColumn<food,String> fName;
    @FXML
    private TableColumn<food, Double> fPrice;
    @FXML
    private TableView<food> restaurantMenu;
    @FXML
    private AnchorPane clientRestaurantPageAnchor;
    @FXML
    private Label rIDLabel=new Label();
    @FXML
    private Label rNameLabel=new Label();
    @FXML
    private Label rRatingLabel=new Label();
    @FXML
    private Label rZipCodeLabel=new Label();
    public ObservableList<food> observableList= FXCollections.observableArrayList();
    public  ObservableList<String> observableList2;
    @FXML
    private ListView<String> ord;
    private ArrayList<String> orders;
    private restaurant r;
    private Scene scene;

    public clientRestaurantPage(restaurant r){
        this.r=r;
        rNameLabel.setText(this.r.getName());
        rIDLabel.setText(Integer.toString(this.r.getId()));
        rRatingLabel.setText(Double.toString(this.r.getScore()));
        rZipCodeLabel.setText(this.r.getzipCode());

        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/RestaurantPage.fxml"));
        try {
            Parent root=fxmlLoader.load();
            scene=new Scene(root,400,500);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        fCategory.setCellValueFactory(new PropertyValueFactory<food,String>("Category"));
        fName.setCellValueFactory(new PropertyValueFactory<food,String>("Name"));
        fPrice.setCellValueFactory(new PropertyValueFactory<food,Double>("Price"));
        restaurantMenu.setItems(observableList);
    }


    public void changeText(MouseEvent event){
        rNameLabel.setText("hi");
    }

    @FXML
    public void print(MouseEvent event){
       // System.out.println("hi");
    }

    public void updateOrderList(ArrayList<String> orders){
        this.orders=orders;
        observableList2=FXCollections.observableArrayList();
        for(String s:orders)
            observableList2.add(s);
        ord.setItems(observableList2);
    }

}

