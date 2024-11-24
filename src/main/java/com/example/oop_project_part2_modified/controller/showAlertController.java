package com.example.oop_project_part2_modified.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class showAlertController {

    @FXML
    private Button alertBoxCloseButton=new Button();

    @FXML
    private Label alertTextBox=new Label();


    public void showAlert(String alert){
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/showAlert.fxml"));
        Scene scene= null;
        try {
            Parent root=fxmlLoader.load();
            showAlertController temp=fxmlLoader.getController();
            temp.alertTextBox.setText(alert);
            scene = new Scene(root,521,243);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void closeAlertBox(MouseEvent event) {
        Stage stage=(Stage)alertBoxCloseButton.getScene().getWindow();
        stage.close();
    }

}
