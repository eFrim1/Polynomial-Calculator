module com.example.pt2024_30227_frim_eleazar_assignment_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.UI to javafx.fxml;
    exports com.example.UI;
}