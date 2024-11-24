package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.client.client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class customSearchPageController {

    @FXML
    private ListView<String> searchResultListView;
    private ObservableList<String> observableList= FXCollections.observableArrayList();
    private client customer;
    private Stage parentStage;
    private ArrayList<String> searchResultString;

    public customSearchPageController(client customer,ArrayList<String> searchResultString){
        this.customer=customer;
        this.searchResultString=searchResultString;
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2/customSearchPage.fxml"));
        try {
            Scene scene=new Scene(fxmlLoader.load());
            setListView(searchResultString);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setListView(ArrayList<String> searchResultString){
        for(String s:searchResultString) observableList.add(s);
        searchResultListView.setItems(observableList);
    }

    public void setClient(client customer){
        this.customer=customer;
    }

}
