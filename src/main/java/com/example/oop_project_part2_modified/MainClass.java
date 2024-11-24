package com.example.oop_project_part2_modified;

import com.example.oop_project_part2_modified.controller.clientRestaurantPage;
import com.example.oop_project_part2_modified.controller.startingPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainClass  extends Application {

    private HashMap<restaurant,ArrayList<clientRestaurantPage>> restaurantWindows=new HashMap<>();

    @Override
    public void start(Stage stage) throws IOException {
        //File file=new File("coffee.png");  //using File doesn't work,only FileInputStream worked until now,it is generally used to read raw data such as images
        FileInputStream fileInputStream=new FileInputStream("src/main/resources/com/example/oop_project_part2_modified/coffee.png");
        //FileInputStream fileInputStream=new FileInputStream("src/main/resources/com/example/oop_project_part2/image/coffee.png"); //three of these code also work ,you just have to be careful that filepath search starts at src
        //FileInputStream fileInputStream=new FileInputStream("src/main/java/com/example/oop_project_part2/coffee.png");
        //FileInputStream fileInputStream=new FileInputStream("src/main/resources/com/example/oop_project_part2/image/coffee.png");
        //FileInputStream fileInputStream=new FileInputStream("com/example/oop_project_part2/coffee.png"); //This approach won't work as path search for image starts from soruce/src
        Image image=new Image(fileInputStream);
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("startingPage.fxml"));
        Parent root=fxmlLoader.load();
        startingPageController controller=fxmlLoader.getController();
        controller.setRestaurantWindows(restaurantWindows);
        controller.setImageView(image);
        Scene scene=new Scene(root,783,506);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
