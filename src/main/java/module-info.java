module com.example.oop_project_part2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.oop_project_part2_modified to javafx.fxml;
    exports com.example.oop_project_part2_modified;
    opens com.example.oop_project_part2_modified.controller to javafx.fxml,javafx.graphics;
    exports com.example.oop_project_part2_modified.controller;
    exports com.example.oop_project_part2_modified.client;
    exports com.example.oop_project_part2_modified.server;
}