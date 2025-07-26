module sabishiikoto.memorymatchinggame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens sabishiikoto.memorymatchinggame to javafx.fxml;
    exports sabishiikoto.memorymatchinggame;
}