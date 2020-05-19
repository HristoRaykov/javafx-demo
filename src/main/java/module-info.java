module javafx_demo {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    opens javafxdemo to javafx.fxml;
    exports javafxdemo;

}