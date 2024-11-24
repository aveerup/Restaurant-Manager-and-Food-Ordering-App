package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.client.client;
import com.example.oop_project_part2_modified.food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class customSearchPageFoodController implements Initializable {

    @FXML
    private TableView<food> foodSearchTable=new TableView<>();
    @FXML
    private TableColumn<food, String> availableIn=new TableColumn<>();
    @FXML
    private TableColumn<food, String> fCategory=new TableColumn<>();
    @FXML
    private TableColumn<food,String> fName=new TableColumn<>();
    @FXML
    private TableColumn<food, Double> fPrice=new TableColumn<>();
    private ObservableList<food> observableList= FXCollections.observableArrayList();
    private client customer;
    @FXML
    private Button addOrder;
    private HashMap<food,Integer> selectedFoods=new HashMap<>();
    private Integer amount=0;
    private ArrayList<food> searchResultFood;

    public customSearchPageFoodController(client customer,ArrayList<food> searchResultFood){
        this.customer=customer;
        this.searchResultFood=searchResultFood;
        setFoodSearchTable();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/customSearchPageFoodController.fxml"));
        Parent root= null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //public void setClient(client customer){ this.customer=customer; }

    void setFoodSearchTable(){
        fName.setCellValueFactory(new PropertyValueFactory<food,String>("Name"));
        availableIn.setCellValueFactory(new PropertyValueFactory<food,String>("restaurantName"));
        fCategory.setCellValueFactory(new PropertyValueFactory<food,String>("Category"));
        fPrice.setCellValueFactory(new PropertyValueFactory<food,Double>("Price"));

        for(food f: searchResultFood) observableList.add(f);
        foodSearchTable.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //selectedFoods.put("noOrder",0);
        //foodSearchTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        foodSearchTable.getSelectionModel().selectedItemProperty().addListener((obs,oldSelection,newSelection)->
        {
            food f=foodSearchTable.getSelectionModel().getSelectedItem();
            //String fName=f.getName();
            System.out.println(f.getName());
            try{
                amount=selectedFoods.get(f);
                selectedFoods.put(f,++amount);
                System.out.println(fName);
            }catch(NullPointerException e){
                amount=0;
                selectedFoods.put(f,++amount);
                System.out.println(fName);
            }
        });
    }

    @FXML
    public void addOrder(MouseEvent event){
        customer.setOrder(selectedFoods);
        for(Map.Entry e:selectedFoods.entrySet())
            System.out.println(e.getKey()+" "+e.getValue());
        customer.sendOrderToServer();
    }

}

