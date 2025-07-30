module sabishiikoto.memorymatchinggame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.xml.dom;


    opens sabishiikoto.memorymatchinggame to javafx.fxml;
    exports sabishiikoto.memorymatchinggame;
}