
module com.pharmacy {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    // requires com.itextpdf.kernel;
    // requires com.itextpdf.layout;
    requires org.slf4j;

    opens com.pharmacy.controller to javafx.fxml;
    opens com.pharmacy.Model to javafx.base;
    
    exports com.pharmacy;
    exports com.pharmacy.controller;
}

