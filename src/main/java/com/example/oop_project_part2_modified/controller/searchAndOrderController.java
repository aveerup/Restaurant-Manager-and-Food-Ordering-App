package com.example.oop_project_part2_modified.controller;

import com.example.oop_project_part2_modified.client.client;
import com.example.oop_project_part2_modified.food;
import com.example.oop_project_part2_modified.restaurant;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class searchAndOrderController implements Initializable {

    private showAlertController alert=new showAlertController();
    private client customer;
    private Stage parentStage=new Stage();
    private ArrayList<restaurant> loggedInRestaurant=new ArrayList<>();
    private String selectedRestaurantName;
    @FXML
    private ListView<String> listViewRestaurant=new ListView<>();
    ObservableList<String> observableList= FXCollections.observableArrayList();
    @FXML
    private Button searchButton;
    @FXML
    private RadioButton restaurantRadioButton=new RadioButton();
    @FXML
    private RadioButton foodRadioButton=new RadioButton();
    @FXML
    private RadioButton category=new RadioButton();
    @FXML
    private RadioButton Name=new RadioButton();
    @FXML
    private RadioButton Score=new RadioButton();
    @FXML
    private RadioButton Zipcode=new RadioButton();
    @FXML
    private RadioButton Price=new RadioButton();
    @FXML
    private RadioButton RestaurantRadioButton4;
    private boolean selectRestaurant;
    private boolean selectFood;
    private boolean selectCategory;
    private boolean selectName;
    private boolean selectScore;
    private boolean selectZipcode;
    private boolean selectPrice;
    @FXML
    private Button selectRestaurantButton=new Button();
    @FXML
    private TextField selectedREstaurant=new TextField();
    @FXML
    private TextField searchText=new TextField();
    private ArrayList<Integer> searchResult=new ArrayList<>();
    private ArrayList<String> searchResultString=new ArrayList<>();
    private ArrayList<food> searchResultFood=new ArrayList<food>();
    private ArrayList<RestaurantPageController> restaurantPageControllers=new ArrayList<>();
    private ArrayList<customSearchPageFoodController> cSPageFoodControllers=new ArrayList<>();
    private ArrayList<customSearchPageController> cSPageControllers=new ArrayList<>();
    @FXML
    private Button addToOrder;



    public void setParentStage(Stage parentStage){
        this.parentStage=parentStage;
    }
    public Stage getParentStage(){ return this.parentStage; }
//    public void setClient(client customer){ this.customer=customer; }
    public client getClient(){ return this.customer; }
    //public void setLoggedInRestaurant(ArrayList<restaurant> loggedInRestaurant){ this.loggedInRestaurant=loggedInRestaurant; }


    public searchAndOrderController(client customer){
        this.customer=customer;
        this.loggedInRestaurant=customer.getLoggedInRestaurant();
        Stage stage=new Stage();
        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/searchAndOrder.fxml"));
        Parent root= null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene=new Scene(root,824,660);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void selectRestaurant(MouseEvent event) {
//        Node node=(Node)event.getSource();
//        Stage parentStage=(Stage) node.getScene().getWindow();
//        parentStage.hide();
//        Stage stage=new Stage();
//        FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("/com/example/oop_project_part2_modified/restaurantPage.fxml"));
//        try {
//            Parent root=fxmlLoader.load();
//            RestaurantPageController controller=fxmlLoader.getController();
//            controller.setParentStage(parentStage);
//            controller.setClient(customer);
//            Scene scene=new Scene(root,600,700);
//            stage.setScene(scene);
//            controller.setRFoodList(selectedRestaurantName);
//            controller.setRestaurantPage();
//            stage.show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        restaurant tempRestaurant=customer.getDataBase().getRestaurant(customer.getDataBase().searchRestaurantByName(selectedRestaurantName).get(0));
        RestaurantPageController tempRPageController=new RestaurantPageController(tempRestaurant);
        restaurantPageControllers.add(tempRPageController);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> rNames=new ArrayList<>();

        for(restaurant r: loggedInRestaurant) rNames.add(r.getName());
        System.out.println("searchAndController "+1.1);
        for(String s:rNames) observableList.add(s);
        //for(restaurant r:loggedInRestaurant) System.out.println("searchAndOrder "+r.getName());
        //for(restaurant r:loggedInRestaurant )listViewRestaurant.getItems().add(r.getName());
        listViewRestaurant.getItems().addAll(observableList);
        listViewRestaurant.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            String selectedItem = listViewRestaurant.getSelectionModel().getSelectedItem();
            selectedREstaurant.setText(selectedItem);
            selectedRestaurantName=selectedItem;
        });
    }

    public void updateListView(){
        ArrayList<String> rNames=new ArrayList<>();
        for(restaurant r: loggedInRestaurant) rNames.add(r.getName());
        System.out.println("searchAndController "+1.1);
        for(String s:rNames) observableList.add(s);
        //for(restaurant r:loggedInRestaurant) System.out.println("searchAndOrder "+r.getName());
        //for(restaurant r:loggedInRestaurant )listViewRestaurant.getItems().add(r.getName());
        listViewRestaurant.getItems().addAll(observableList);
    }

    public void searchButtonClicked(MouseEvent event){
        searchResult.clear();
        if(restaurantRadioButton.isSelected() && category.isSelected()){
            System.out.println("searchAndOrder "+1.3);
            searchResult=customer.getDataBase().searchRestaurantByCategory(searchText.getText());
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getRestaurant(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match) noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult) {
                searchResultString.add(customer.getDataBase().getRestaurant(i).getName());
                System.out.println(i);
            }
            for(String s:searchResultString) System.out.println(s);
        }
        else if(restaurantRadioButton.isSelected() && Name.isSelected()){
            searchResult=customer.getDataBase().searchRestaurantByName(searchText.getText());
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getRestaurant(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match) noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult) searchResultString.add(customer.getDataBase().getRestaurant(i).getName());
        }
        else if(restaurantRadioButton.isSelected() && Price.isSelected()){
            searchResult=customer.getDataBase().searchRestaurantByPrice(searchText.getText());
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getRestaurant(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match) noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult) searchResultString.add(customer.getDataBase().getRestaurant(i).getName());
        }
        else if(restaurantRadioButton.isSelected() && Zipcode.isSelected()){
            searchResult=customer.getDataBase().searchRestaurantByZipCode(searchText.getText());
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getRestaurant(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match) noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult) searchResultString.add(customer.getDataBase().getRestaurant(i).getName());
        }
        else if(restaurantRadioButton.isSelected() && Score.isSelected()){
            String[] parts=searchText.getText().split("\\s");
            searchResult=customer.getDataBase().searchRestaurantByScore(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getRestaurant(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match) noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult) searchResultString.add(customer.getDataBase().getRestaurant(i).getName());
        }
        else if(foodRadioButton.isSelected() && Name.isSelected()) {
            searchResult = customer.getDataBase().searchFoodByName(searchText.getText());
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getFood(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match)
                    noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for (int i : searchResult) {
                searchResultFood.add(customer.getDataBase().getFood(i));
                customer.getDataBase().getFood(i).setRestaurantName(customer.getDataBase().getRestaurant(customer.getDataBase().searchRestaurantById(customer.getDataBase().getFood(i).getId()).get(0)).getName());
            }
        }
        else if(foodRadioButton.isSelected() && category.isSelected()){
            //System.out.println("SearchAndOrder "+1.6+" "+searchText.getText()+" "+searchResult.size());
            searchResult=customer.getDataBase().searchFoodByCategory(searchText.getText());
            System.out.println("serchREsultsize "+searchResult.size());
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    System.out.println("resId "+r.getId()+" resName "+r.getName()+" "+customer.getDataBase().getFood(searchResult.get(i)).getId());
                    if(customer.getDataBase().getFood(searchResult.get(i)).getId()==r.getId()){
                        System.out.println("resId "+r.getId()+" resName "+r.getName()+" "+customer.getDataBase().getFood(searchResult.get(i)).getId());
                        match=true;
                    }
                }
                if(!match) {
                    noMatchIndex.add(i);
                }
                match=false;
            }
            System.out.println("noMatchIndexSize "+noMatchIndex.size());
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult) {
                searchResultFood.add(customer.getDataBase().getFood(i));
                customer.getDataBase().getFood(i).setRestaurantName(customer.getDataBase().getRestaurant(customer.getDataBase().searchRestaurantById(customer.getDataBase().getFood(i).getId()).get(0)).getName());
            }
            for(restaurant r:loggedInRestaurant) System.out.println(r.getName());
            //new customSearchPageController(searchResultString);
        }
        else if(foodRadioButton.isSelected() && Price.isSelected()){
            String[] parts=searchText.getText().split("\\s");
            searchResult=customer.getDataBase().searchFoodBetRange(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));
            boolean match=false;
            ArrayList<Integer> noMatchIndex=new ArrayList<>();
            for(int i=0;i<searchResult.size();i++){
                for(restaurant r:loggedInRestaurant){
                    if(customer.getDataBase().getFood(searchResult.get(i)).getId()==r.getId()){
                        match=true;
                    }
                }
                if(!match)
                    noMatchIndex.add(i);
                match=false;
            }
            for(int i=noMatchIndex.size()-1;i>-1;i--) searchResult.remove((int)noMatchIndex.get(i));
            for(int i:searchResult){
                searchResultFood.add(customer.getDataBase().getFood(i));
                customer.getDataBase().getFood(i).setRestaurantName(customer.getDataBase().getRestaurant(customer.getDataBase().searchRestaurantById(customer.getDataBase().getFood(i).getId()).get(0)).getName());
            }
            //new customSearchPageController(searchResultString);
        }
        else if(foodRadioButton.isSelected() && Score.isSelected()) alert.showAlert("Score is only applicable for restaurant ");
        else if(foodRadioButton.isSelected() && Zipcode.isSelected()) alert.showAlert("Zipcode is only applicable for restaurant ");

        if(restaurantRadioButton.isSelected()) {
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/oop_project_part2/customSearchPage.fxml"));
//            try {
//                System.out.println("customSPCon " + 1.1);
//                Parent root = fxmlLoader.load();
//                Scene scene = new Scene(root);
//                customSearchPageController controller = fxmlLoader.getController();
//                stage.setTitle("search result");
//                System.out.println("customSPCon " + 1.1);
//                controller.setListView(searchResultString);
//                controller.setClient(customer);
//                stage.setScene(scene);
//                stage.show();
//                searchResultString.clear();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            customSearchPageController cSPageController=new customSearchPageController(customer,searchResultString);
            cSPageControllers.add(cSPageController);
            searchResultString.clear();
        }
        if(foodRadioButton.isSelected() && !Score.isSelected() && !Zipcode.isSelected()){
//            Stage stage = new Stage();
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/oop_project_part2/customSearchPageFood.fxml"));
//            try {
//                System.out.println("customSPCon " + 1.1);
//                Parent root = fxmlLoader.load();
//                Scene scene = new Scene(root);
//                customSearchPageFoodController controller = fxmlLoader.getController();
//                stage.setTitle("search result");
//                System.out.println("customSPCon " + 1.1);
//                for(food f:searchResultFood) System.out.println(" food "+f.getName()+" "+f.getRestaurantName());
//                controller.setClient(customer);
//                controller.setFoodSearchTable(searchResultFood);
//                stage.setScene(scene);
//                stage.show();
//                searchResultString.clear();
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            customSearchPageFoodController cSPageController=new customSearchPageFoodController(customer,searchResultFood);
            cSPageFoodControllers.add(cSPageController);
            searchResultFood.clear();
        }
    }
}
