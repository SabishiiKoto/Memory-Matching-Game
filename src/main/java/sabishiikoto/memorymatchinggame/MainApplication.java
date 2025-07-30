package sabishiikoto.memorymatchinggame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 807, 609);
        stage.setTitle("Memory Matching Game!");
        stage.setScene(scene);
        String avatar = "/Assets/avatar.png";
        stage.getIcons().add(new Image(getClass().getResource(avatar).toExternalForm()));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}