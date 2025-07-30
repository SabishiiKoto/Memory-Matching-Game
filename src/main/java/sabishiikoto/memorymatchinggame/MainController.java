package sabishiikoto.memorymatchinggame;

import java.util.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;

public class MainController {

    @FXML
    private MenuBar menuBar;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button nextButton;

    @FXML
    private GridPane gridPaneForMatching;

    @FXML
    private Label labelForError;

    @FXML
    private Label labelForTitle;

    private static final ArrayList<Image> imageList = new ArrayList<>();
    private static Image[][] map = null;

    private static final String[] patternList = {"pattern.png", "pattern1.png", "pattern2.png", "pattern3.png", "pattern4.png","pattern5.png", "pattern6.png"};
    private myMouseClickerHandler previousImage = null;
    private int win;
    private int level;
    @FXML
    void aboutTrigger(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About This Memory Matching Game!");
        alert.setHeaderText("Information and Contact");
        alert.setContentText("I hope you enjoy the game!\nFor contact, check out my GitHub:\nhttps://github.com/SabishiiKoto");
        alert.showAndWait();
    }

    @FXML
    void vintageColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #F6BD60;");
        labelForTitle.setStyle("-fx-text-fill: #84A59D;");
        anchorPane.setStyle("-fx-background-color: #F7EDE2;");
        labelForError.setStyle("-fx-background-color: #F7EDE2;-fx-text-fill: #F28482;");
    }

    @FXML
    void blueColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #0196C1;");
        labelForTitle.setStyle("-fx-text-fill: #046C95;");
        anchorPane.setStyle("-fx-background-color: #B3E0EE;");
        labelForError.setStyle("-fx-background-color: #B3E0EE;-fx-text-fill: #083346;");
    }

    @FXML
    void greenColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #549895;");
        labelForTitle.setStyle("-fx-text-fill: #387271;");
        anchorPane.setStyle("-fx-background-color: #8EBCB1;");
        labelForError.setStyle("-fx-background-color: #8EBCB1;-fx-text-fill: #245254;");
    }

    @FXML
    void pinkColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #EABEC3;");
        labelForTitle.setStyle("-fx-text-fill: #DD868C;");
        anchorPane.setStyle("-fx-background-color: #F5DDE0;");
        labelForError.setStyle("-fx-background-color: #F5DDE0;-fx-text-fill: #D0637C;");
    }

    @FXML
    void blackColorTrigger(ActionEvent event) {
        menuBar.setStyle("-fx-background-color: #2B2F6C;");
        labelForTitle.setStyle("-fx-text-fill: #DE978F;");
        anchorPane.setStyle("-fx-background-color: #564779;");
        labelForError.setStyle("-fx-background-color: #564779;-fx-text-fill: #DE978F;");
    }

    @FXML
    void easyTrigger(ActionEvent event) {
        labelForError.setText("");
        gridPaneMapping(4);
    }

    @FXML
    void exitTrigger(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void expertTrigger(ActionEvent event) {
        labelForError.setText("");
        gridPaneMapping(10);
    }

    @FXML
    void hardTrigger(ActionEvent event) {
        labelForError.setText("");
        gridPaneMapping(8);
    }

    @FXML
    void mediumTrigger(ActionEvent event) {
        labelForError.setText("");
        gridPaneMapping(6);
    }
    @FXML
    void nextTrigger(ActionEvent event) {
        labelForError.setText("");
        labelForTitle.setText("MEMORY MATCHING!");
        nextButton.setVisible(false);
        gridPaneMapping(level);
    }


    public static ArrayList<Image> createTempList(){
        ArrayList<Image> tempList = new ArrayList<>();
        for (Image image : imageList){
            tempList.add(image);
        }
        return tempList;
    }
    public static ArrayList<Image> modeImageList (int mode){
        ArrayList<Image> tempList = createTempList();
        ArrayList<Image> modeimageList = new ArrayList<>();
        for (int i = 0; i < mode*mode/2; i++){
            Image image = imagePick(tempList);
            tempList.remove(image);
            modeimageList.add(image);
            modeimageList.add(image);
        }
        return modeimageList;
    }
    public static Image imagePick(ArrayList<Image> tempList){
            Random random = new Random();
            int index = random.nextInt(tempList.size());
            Image image = tempList.get(index);
            return image;
    }
    public Image floor = null;

    public static Image[][] imageMapSetUp(ArrayList<Image> tempList, int mode){
        map = new Image[mode][mode];
        for (int row = 0; row < mode; row++){
            for (int column = 0; column < mode; column++){
                Image image = imagePick(tempList);
                map[row][column] = image;
                tempList.remove(image);
            }
        }
        return map;
    }

    void gridPaneMapping(int mode){
        win = 0;
        level = mode;
        nextButton.setVisible(false);
        gridPaneForMatching.getColumnConstraints().clear();
        gridPaneForMatching.getRowConstraints().clear();
        gridPaneForMatching.getChildren().clear();
        ArrayList<Image> modeList = modeImageList(mode);
        map = imageMapSetUp(modeList, mode);
        Random random = new Random();
        int index = random.nextInt(patternList.length);
        String pattern = "/Assets/" + patternList[index];
        floor = new Image(getClass().getResource(pattern).toExternalForm());
        double size;
        if (mode == 4){
            size = 127;
        }
        else if (mode == 6){
            size = 86;
        }
        else if (mode == 8){
            size = 64.5;
        }
        else{
            size = 52;
        }
        for (int row = 0; row < mode; row++){
            for (int column = 0; column < mode; column++){
                ImageView imageView = new ImageView(floor);
                imageView.setFitHeight(size);
                imageView.setFitWidth(size);
                imageView.setPickOnBounds(true);

                imageView.setOnMouseClicked(new myMouseClickerHandler(row, column, imageView));
                gridPaneForMatching.add(imageView, column, row);
            }
        }
    }
    public class myMouseClickerHandler implements EventHandler {
        private int row;
        private int column;
        private ImageView imageView;
        public myMouseClickerHandler(int row, int column, ImageView imageView){
            this.row = row;
            this.column = column;
            this.imageView = imageView;
        }

        public int getRow (){
            return this.row;
        }
        public int getColumn(){
            return this.column;
        }
        @Override
        public void handle(Event event) {
            if (previousImage != null) {
                if (this.row == previousImage.getRow() && this.column == previousImage.getColumn()) {
                    if (this.imageView.getImage().equals(floor)) {
                        this.imageView.setImage(map[row][column]);
                        previousImage = this;
                    } else if (this.imageView.getImage().equals(map[row][column])) {
                        this.imageView.setImage(floor);
                        previousImage = null;
                    }
                } else {
                    this.imageView.setImage(map[row][column]);
                    if (previousImage.imageView.getImage().equals(this.imageView.getImage())) {
                        PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                        pause.setOnFinished(e -> {
                            try{
                                if (previousImage.imageView != null) {
                                    previousImage.imageView.setImage(null);
                                    this.imageView.setImage(null);
                                    previousImage.imageView.setOnMouseClicked(null);
                                    this.imageView.setOnMouseClicked(null);
                                    previousImage = null;
                                    win++;
                                    int couple = level*level/2;
                                    labelForTitle.setText(win + "/" + couple);
                                    if (win == couple){
//                            labelForError.setTextFill(Color.STEELBLUE);
                                        labelForError.setText("You won, congratulation!!!");
                                        nextButton.setVisible(true);
                                    }
                                }
                            }
                            catch(Exception error){

                            }
                        });
                        pause.play();

                    } else {
                        // Copilot helped with the timing thing.
                            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                            pause.setOnFinished(e -> {
                                try{
                                    if (previousImage.imageView != null) {
                                        previousImage.imageView.setImage(floor);
                                        this.imageView.setImage(floor);
                                        previousImage = null;
                                    }
                                }
                                catch(Exception error){

                                }
                            });
                            pause.play();
                    }
                }
            }
            else{
                previousImage = this;
                this.imageView.setImage(map[row][column]);
            }
        }
    }

    @FXML
    void initialize(){
        // Create the image list
        try {
            for (int group = 1; group < 11; group++) {
                for (int i = 1; i < 11; i++) {
                    String name = "/Assets/" + "Group" + group + "-" + i + ".png";
                    Image image = new Image(getClass().getResource(name).toExternalForm());
                    imageList.add(image);
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        menuBar.setStyle("-fx-background-color: #549895;");
        labelForTitle.setStyle("-fx-text-fill: #387271;");
        anchorPane.setStyle("-fx-background-color: #8EBCB1;");
        labelForError.setStyle("-fx-background-color: #8EBCB1;-fx-text-fill: #245254;");
        gridPaneMapping(4);
        nextButton.setVisible(false);
        labelForError.setText("Hello! To start playing select Mode (top-left corner) and pick a level.");
    }
}
