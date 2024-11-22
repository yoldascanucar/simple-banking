module com.example.simplebanking {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;  // Add this
    requires java.sql;               // Add this
    requires java.naming;            // Add this for JNDI support


    opens banking to javafx.fxml;
    opens fxml to javafx.fxml;
    opens entity to org.hibernate.orm.core;

    exports banking;
    exports controllers;
    opens controllers to javafx.fxml;
}
